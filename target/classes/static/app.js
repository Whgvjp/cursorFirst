new Vue({
  el: '#app',
  data() {
    return {
      tab: 'books',
      books: [],
      query: { name: '', author: '', category: '' },
      newBook: {
        name: '', author: '', isbn: '', publisher: '',
        publishDate: '', category: '', description: ''
      },
      borrow: { bookId: null, userId: null, borrowDays: 14, remark: '' },
      action: { borrowId: null, days: 7 },
      overdue: [],
      toast: { show: false, message: '', type: '' }
    }
  },
  created() {
    this.loadAll()
  },
  methods: {
    api() { return axios.create({ baseURL: '' }) },
    notify(message, type = 'info') {
      this.toast = { show: true, message, type }
      setTimeout(() => { this.toast.show = false }, 2000)
    },
    statusText(s) { return s === 0 ? '可借阅' : (s === 1 ? '已借出' : '已下架') },
    statusClass(s) { return s === 0 ? 'ok' : (s === 1 ? 'warn' : 'muted') },

    async loadAll() {
      try {
        const { data } = await this.api().get('/api/books')
        if (data.code === 200) this.books = data.data || []
      } catch (e) { this.notify('加载失败', 'error') }
    },
    async searchBooks() {
      try {
        const params = { ...this.query }
        const { data } = await this.api().get('/api/books/search', { params })
        if (data.code === 200) this.books = data.data || []
      } catch (e) { this.notify('搜索失败', 'error') }
    },
    async addBook() {
      try {
        if (!this.newBook.name || !this.newBook.author || !this.newBook.isbn || !this.newBook.publisher || !this.newBook.publishDate || !this.newBook.category) {
          this.notify('请完整填写必填项', 'error');
          return;
        }
        const payload = { ...this.newBook }
        // datetime-local 返回如 2025-08-25T15:15，没有秒。保持 ISO-8601 的 T，不要替换为空格。
        if (payload.publishDate) {
          // 若没有秒，则补 ":00"
          if (/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/.test(payload.publishDate)) {
            payload.publishDate = payload.publishDate + ':00'
          }
        }
        const { data } = await this.api().post('/api/books', payload)
        if (data.code === 200) {
          this.notify('新增成功', 'success')
          this.newBook = { name: '', author: '', isbn: '', publisher: '', publishDate: '', category: '', description: '' }
          this.loadAll()
        } else {
          this.notify(data.message || '新增失败', 'error')
        }
      } catch (e) { this.notify(e?.response?.data?.message || '新增失败', 'error') }
    },
    async removeBook(id) {
      if (!confirm('确认删除该图书？')) return
      try {
        const { data } = await this.api().delete(`/api/books/${id}`)
        if (data.code === 200) {
          this.notify('删除成功', 'success')
          this.loadAll()
        } else {
          this.notify(data.message || '删除失败', 'error')
        }
      } catch (e) { this.notify(e?.response?.data?.message || '删除失败', 'error') }
    },
    async doBorrow() {
      try {
        const payload = { ...this.borrow }
        const { data } = await this.api().post('/api/borrows', payload)
        if (data.code === 200) this.notify('借阅成功', 'success')
        else this.notify(data.message || '借阅失败', 'error')
        this.loadAll()
      } catch (e) { this.notify(e?.response?.data?.message || '借阅失败', 'error') }
    },
    async doReturn() {
      try {
        const id = this.action.borrowId
        const { data } = await this.api().put(`/api/borrows/${id}/return`)
        if (data.code === 200) this.notify('归还成功', 'success')
        else this.notify(data.message || '归还失败', 'error')
        this.loadAll()
      } catch (e) { this.notify(e?.response?.data?.message || '归还失败', 'error') }
    },
    async doRenew() {
      try {
        const id = this.action.borrowId
        const days = this.action.days
        const { data } = await this.api().put(`/api/borrows/${id}/renew`, null, { params: { additionalDays: days } })
        if (data.code === 200) this.notify('续借成功', 'success')
        else this.notify(data.message || '续借失败', 'error')
      } catch (e) { this.notify(e?.response?.data?.message || '续借失败', 'error') }
    },
    async loadOverdue() {
      try {
        const { data } = await this.api().get('/api/borrows/overdue')
        if (data.code === 200) this.overdue = data.data || []
      } catch (e) { this.notify('加载逾期失败', 'error') }
    }
  }
})
