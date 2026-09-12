# NixOS module for the backend. Exposed by the flake as `nixosModules.backend`.
#
# It deliberately binds a private address and opens no ports by default: putting a TLS-terminating reverse
# proxy in front, or reaching it over a VPN, is left to the host configuration.
self:
{ config, lib, pkgs, ... }:

let
  cfg = config.services.tyrianBackend;
in
{
  options.services.tyrianBackend = {
    enable = lib.mkEnableOption "the Tyrian fullstack ZIO-HTTP backend";

    package = lib.mkOption {
      type = lib.types.package;
      default = self.packages.${pkgs.stdenv.hostPlatform.system}.backend;
      defaultText = lib.literalExpression "self.packages.\${system}.backend";
      description = "The backend package to run.";
    };

    host = lib.mkOption {
      type = lib.types.str;
      default = "127.0.0.1";
      example = "10.100.1.1";
      description = ''
        Address to bind. Loopback by default; set a private address (a VPN one, say) to reach it from
        elsewhere. Binding a public address is possible but leaves the API unauthenticated at the edge.
      '';
    };

    port = lib.mkOption {
      type = lib.types.port;
      default = 8080;
      description = "TCP port to listen on.";
    };

    allowedOrigins = lib.mkOption {
      type = lib.types.listOf lib.types.str;
      default = [ ];
      example = [ "https://app.example.com" ];
      description = ''
        Origins accepted by CORS, which must be where the *frontend* is served from -- scheme and port
        included. Leave empty to keep the application's own default (the Vite dev server).

        This only covers the backend half. The frontend bakes its API URL in at build time from
        BACKEND_BASE_URL, so a split-origin deployment has to set that when building the frontend too.
      '';
    };

    environmentFile = lib.mkOption {
      type = lib.types.nullOr lib.types.path;
      default = null;
      example = "/run/secrets/backend-env";
      description = ''
        Path to an environment file read by systemd, for values that should not land in the world-readable
        Nix store -- above all BACKEND_JWT_SECRET, which signs and verifies session tokens and otherwise
        stays at a well-known default from the template.

        Read by systemd as root before privileges are dropped, so it need not be readable by the service.
      '';
    };

    openFirewall = lib.mkOption {
      type = lib.types.bool;
      default = false;
      description = ''
        Open {option}`port` on all interfaces. Off by default. To expose the service on one interface only,
        leave this off and use `networking.firewall.interfaces.<name>.allowedTCPPorts` instead.
      '';
    };
  };

  config = lib.mkIf cfg.enable {
    systemd.services.tyrian-backend = {
      description = "Tyrian fullstack ZIO-HTTP backend";
      after = [ "network.target" ];
      wantedBy = [ "multi-user.target" ];

      environment = {
        BACKEND_HOST = cfg.host;
        BACKEND_PORT = toString cfg.port;
      } // lib.optionalAttrs (cfg.allowedOrigins != [ ]) {
        BACKEND_CORS_ORIGINS = lib.concatStringsSep "," cfg.allowedOrigins;
      };

      serviceConfig = lib.optionalAttrs (cfg.environmentFile != null) {
        EnvironmentFile = cfg.environmentFile;
      } // {
        ExecStart = lib.getExe cfg.package;
        Restart = "on-failure";
        RestartSec = 5;

        DynamicUser = true;
        ProtectSystem = "strict";
        ProtectHome = true;
        PrivateTmp = true;
        PrivateDevices = true;
        NoNewPrivileges = true;
        ProtectKernelTunables = true;
        ProtectKernelModules = true;
        ProtectControlGroups = true;
        RestrictAddressFamilies = [ "AF_INET" "AF_INET6" ];
        RestrictNamespaces = true;
        LockPersonality = true;
        SystemCallArchitectures = "native";
      };
    };

    networking.firewall.allowedTCPPorts = lib.mkIf cfg.openFirewall [ cfg.port ];
  };
}
