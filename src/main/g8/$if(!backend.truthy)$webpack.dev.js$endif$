const { merge } = require('webpack-merge');
const { scalaAppEntrypoint } = require('./scala.js')
const common = require('./webpack.common.js')
const env = 'development'
module.exports = merge(common, {
  mode: env,
  output: {
    filename: 'dev.[name].js',
  },
  entry: [
    scalaAppEntrypoint(env)
  ],
  devServer: {
    port: 9876
  }
})
