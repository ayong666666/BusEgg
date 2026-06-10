import request from './request'

export const leadApi = {
  list: params => request.get('/admin/leads', { params }),
  updateStatus: (id, data) => request.put(`/admin/leads/${id}/status`, data),
  updateRemark: (id, data) => request.put(`/admin/leads/${id}/remark`, data)
}

