import Vue from 'vue'
import Vuex from 'vuex'
import settings from './modules/settings'
import app from './modules/app'
import user from './modules/user'
import getters from './getters'
import permission from '@/store/modules/permission'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user,
    permission
  },
  getters
})

export default store
