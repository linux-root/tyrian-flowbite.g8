import { defineConfig } from 'vite';
import path from 'path';

export default defineConfig(({ command }) => {
  return {
    resolve: {
      alias: {
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
