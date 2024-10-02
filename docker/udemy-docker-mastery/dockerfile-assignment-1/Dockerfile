# you should use the `node` official image, with the alpine 6.x branch (`node:6-alpine`)
FROM node:6-alpine

# This app listens on port 3000, but the container should listen on port 80 of the Docker host, so it will respond to [http://localhost:80](http://localhost:80) on your computer
EXPOSE 3000

# Then it should use the alpine package manager to install tini: `apk add --no-cache tini`.
RUN apk add --update tini

# Then it should create directory /usr/src/app for app files with `mkdir -p /usr/src/app`, or with the Dockerfile command `WORKDIR /usr/src/app`.
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY package.json package.json

# Node.js uses a "package manager", so it needs to copy in package.json file.
# Then it needs to run 'npm install' to install dependencies from that file.
# To keep it clean and small, run `npm cache clean --force` after the above, in the same RUN command.
RUN npm install && npm cache clean --force

# Then it needs to copy in all files from current directory into the image.
COPY . .

# Then it needs to start the container with the command `/sbin/tini -- node ./bin/www`. Be sure to use JSON array syntax for CMD. (`CMD [ "something", "something" ]`)
# In the end you should be using FROM, RUN, WORKDIR, COPY, EXPOSE, and CMD commands
CMD [ "tini", "--", "node", "./bin/www" ]
