const { merge } = require('webpack-merge');
const { scalaAppEntrypoint } = require('./scala.js')
const common = require('./webpack.common.js')
const env = 'production'
module.exports = merge(common, {
  mode: env,
  output: {
    filename: 'prod.[name].[contenthash].js',
  },
  entry: {
    tyrianApp: scalaAppEntrypoint(env),
  },
  devServer: {
    port: 6789
  }
})
