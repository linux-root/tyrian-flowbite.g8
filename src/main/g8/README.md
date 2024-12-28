## Scala.js Project Template with Tyrian and Flowbite

Welcome to the Scala.js Project Template! This template sets up a Scala.js project
using the [Tyrian](https://tyrian.indigoengine.io/) and Flowbite for UI components.
Tyrian is a powerful, type-safe framework for building web applications
with Scala.js, and Flowbite is a Tailwind CSS component library that provides
beautiful and customizable UI elements.

### Getting Started

#### 1. Show all useful commands

```bash
sbt
```

#### 2. Watch and compile Scala code upon changes

```bash
sbt watch
```

#### 3. Start Webpack dev server on `http://localhost:9876`

```bash
sbt dev
```

- Publish docker image Locally :

```bash
# Nginx server serving your SPA inside docker container will
# be listening on port 80
# To run docker container: docker run -p 8080:80 .....
sbt dpl
```

## Template license

Written in 2024 by Watson Dinh <mr.kurro@gmail.com>

To the extent possible under law, the author(s) have dedicated
all copyright and related and neighboring rights to this template
to the public domain worldwide. This template is distributed without
any warranty. See <https://creativecommons.org/publicdomain/zero/1.0/>.
[g8]: <https://www.foundweekends.org/giter8/>
