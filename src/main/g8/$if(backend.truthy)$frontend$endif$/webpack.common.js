const HtmlWebpackPlugin = require('html-webpack-plugin')
const CopyWebpackPlugin = require('copy-webpack-plugin')

const path = require('path');

const resourcesPath = path.join('src', 'main', 'resources')
const jsPath = path.join('src', 'main', 'js')

module.exports = {
  resolve: {
    alias: {
      "resources": path.resolve(resourcesPath),
      "js": path.resolve(jsPath),
    },
    fallback: { "crypto": false }
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    publicPath: '/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'dist'),
    },
    compress: true,
    historyApiFallback: true
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          'style-loader',
          'css-loader',
          'postcss-loader'
        ],
      },
    ],
  },

  plugins: [
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, "./index.html")
    }),
    new CopyWebpackPlugin({
      patterns: [
        { from: './public' }
      ]
    })
  ],
};
