import request from './request'

export const siteApi = {
  submitLead: data => request.post('/site/leads', data),
  getNews: () => request.get('/site/news'),
  getProducts: () => request.get('/site/products')
}

