const path = require('path')
module.exports = {
  chainWebpack: config => {
    // 给svg规则增加⼀个排除选项
    config.module
      .rule('svg')
      .exclude.add(path.resolve(__dirname, './src/icons'))

    // 新增icons规则，设置svg-sprite-loader处理icons⽬录中的svg
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(path.resolve(__dirname, './src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({ symbolId: 'icon-[name]' })
      .end()
      .use('svgo-loader')
      .loader('svgo-loader')

    // config.resolve.alias.set('@img', path.resolve(__dirname, 'src/assets/img/'))
  },
}

