const scalaOutput = import.meta.env.DEV ?
  './target/scala-$scala_version$/frontend-fastopt/main.js' : ' ./target/scala-$scala_version$/frontend-opt/main.js'

import(/* @vite-ignore */scalaOutput);
