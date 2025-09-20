import { defineConfig } from 'vite';
import path from 'path';

export default defineConfig(({ command }) => {
  const scalaVersion = '$scala_version$'
  return {
    resolve: {
      alias: {
        "scalajs": command === "serve" ? `./target/scala-\${scalaVersion}/frontend-fastopt/main.js` : `./target/scala-\${scalaVersion}/frontend-opt/main.js`,
        "resources": path.resolve(__dirname, "./src/main/resources"),
        "js": path.resolve(__dirname, "./src/main/js"),
      }
    },
    server: {
      port: 9876,
      historyApiFallback: true
    },
  };
});
