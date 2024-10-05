const path = require('path')
const scalaVersion = '3.5.1'
const scalaProjectName = 'tyrian-flowbite'

const scalaAppEntrypoint = (env) => {
  console.log(`Loading output project ${scalaProjectName} scala version ${scalaVersion}`)
  if (env === 'development') {
    return path.resolve(__dirname, `target/scala-${scalaVersion}/${scalaProjectName}-fastopt/main.js`);
  } else if (env === 'production') {
    return path.resolve(__dirname, `target/scala-${scalaVersion}/${scalaProjectName}-opt/main.js`);
  } else {
    console.error(`Loading output scala version ${scalaVersion}. Unknown env ${env}`)
  }
}
exports.scalaAppEntrypoint = scalaAppEntrypoint
