'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:9999"',
  OSS_PATH: '"https://edu-guli-20001006.oss-cn-beijing.aliyuncs.com"'
})
