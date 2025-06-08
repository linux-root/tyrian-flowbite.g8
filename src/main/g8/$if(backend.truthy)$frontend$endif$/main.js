import 'resources/index.css'

if (import.meta.env.DEV) {
  import('./target/scala-$scala_version$/frontend-fastopt/main.js');
} else {
  import('./target/scala-$scala_version$/frontend-opt/main.js');
}
