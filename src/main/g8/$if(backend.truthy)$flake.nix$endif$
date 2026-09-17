{
  description = "ZIO-HTTP backend, packaged for Nix and NixOS";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    sbt-derivation = {
      url = "github:zaninime/sbt-derivation";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };

  outputs = { self, nixpkgs, flake-utils, sbt-derivation }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};

        # Headless: this is a server, and it keeps the runtime closure smaller.
        jdk = pkgs.jdk21_headless;

        # `.jvmopts` asks for an 8 GB heap, which is aimed at interactive development and is far more than a
        # build machine should be asked for. It has to go in both derivations -- the dependency fetch and the
        # build -- because sbt's launcher reads it from the project root either way.
        dropDevJvmOpts = "rm -f .jvmopts";
      in
      {
        packages.backend = sbt-derivation.lib.mkSbtDerivation {
          inherit pkgs;

          pname = "backend";
          version = "0.1.0";
          src = ./.;

          # Hash of the fetched sbt dependencies. It covers external artifacts only, so it does not depend on
          # this project's name or organization -- it is valid as generated, against the pinned flake.lock.
          #
          # Change a dependency in project/Dependencies.scala, or run `nix flake update`, and it goes stale.
          # The build then fails with `hash mismatch`; copy the `got:` value it prints into this line.
          depsSha256 = "sha256-Usvzy3bi2sRwxZbmUE4iUuY9/5WfJVp8ZaRKNu9WB2Y=";

          # The root project deliberately has no `.aggregate(...)`, so the default warmup of `sbt compile`
          # would resolve nothing and leave the real build to hit the network inside the sandbox, where it
          # fails. Name the leaf module instead; `common.jvm` follows via `dependsOn`.
          depsWarmupCommand = ''
            ${dropDevJvmOpts}
            sbt backend/compile
          '';

          postPatch = dropDevJvmOpts;

          nativeBuildInputs = [ pkgs.makeWrapper ];

          # `stage` is sbt-native-packager's JavaAppPackaging output: a launcher script plus the full
          # classpath as jars. No fat-jar step is involved.
          buildPhase = ''
            runHook preBuild
            sbt backend/stage
            runHook postBuild
          '';

          installPhase = ''
            runHook preInstall

            mkdir -p $out
            cp -r backend/target/universal/stage/bin $out/bin
            cp -r backend/target/universal/stage/lib $out/lib
            rm -f $out/bin/*.bat

            wrapProgram $out/bin/backend --set JAVA_HOME ${jdk}

            runHook postInstall
          '';

          meta = {
            description = "ZIO-HTTP backend";
            mainProgram = "backend";
            platforms = pkgs.lib.platforms.unix;
          };
        };

        packages.default = self.packages.${system}.backend;

        devShells.default = pkgs.mkShell {
          packages = [ pkgs.jdk17 pkgs.sbt pkgs.nodejs_22 ];
        };
      })
    // {
      nixosModules.backend = import ./nix/module.nix self;
      nixosModules.default = self.nixosModules.backend;
    };
}
