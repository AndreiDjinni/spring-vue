<template>
    <div v-if="!loading">

        <div class="w-100 my-2 d-flex">
            <div class="w-25">
                <b-form-input v-model="size" class="w-3rem" size="sm" type="text" placeholder="Enter size" />
            </div>
            <div class="w-50">
                <b-pagination-nav v-model="currentPage" size="sm" :use-router="true" align="center"
                                  :link-gen="linkGen" :number-of-pages="totalPages" />
            </div>
            <div class="w-25">
                <b-button size="sm" @click="showAddModal" class="float-right" variant="success">
                    Add employee
                </b-button>
            </div>
        </div>

        <b-table show-empty
                 :striped=true
                 :outlined=true
                 :hover=true
                 :sort-by.sync="sortBy"
                 :sort-desc.sync="sortDesc"
                 :sort-direction="sortDirection"
                 :fields="fields"
                 :items="employees">
            <template slot="id" slot-scope="data">
                {{data.item.id}}
            </template>
            <template slot="firstName" slot-scope="data">
                {{data.item.firstName}}
            </template>
            <template slot="actions" slot-scope="data">
                <div style="display: flex; justify-content: space-between;">
                    <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button size="sm" @click.stop="showUpdateModal(data.item)" variant="warning" class="mr-1">
                        Update
                    </b-button>
                    <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button size="sm" @click.stop="showDeleteModal(data.item)" variant="danger" class="mr-1">
                        Delete
                    </b-button>
                </div>
            </template>
            <template slot="lastName" slot-scope="data">
                {{data.item.lastName}}
            </template>
            <template slot="salary" slot-scope="data">
                {{data.item.salary}}
            </template>
        </b-table>

        <div class="w-100 my-2 d-flex">
            <div class="w-25">
                <b-form-input v-model="size" class="w-3rem" size="sm" type="text" placeholder="Enter size" />
            </div>
            <div class="w-50">
                <b-pagination-nav v-model="currentPage" size="sm" :use-router="true" align="center"
                                  :link-gen="linkGen" :number-of-pages="totalPages" />
            </div>
            <div class="w-25">
                <b-button size="sm" @click="showAddModal" class="float-right" variant="success">
                    Add employee
                </b-button>
            </div>
        </div>

        <b-modal ref="modalAdd" centered @hide="resetAddModal" title="Add employee ...">
            <b-container style="padding-bottom: 10%;">
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">First Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="newEmployee.firstName" type="text" placeholder="Enter first name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Last Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="newEmployee.lastName" type="text" placeholder="Enter last name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Salary</label></b-col>
                    <b-col>
                        <b-form-input v-model="newEmployee.salary" type="text" placeholder="Enter salary"></b-form-input>
                    </b-col>
                </b-row>
            </b-container>
            <div slot="modal-footer" class="w-100">
                <b-btn size="sm" class="float-right px-4" variant="success" @click="addEmployee">
                    Add
                </b-btn>
            </div>
        </b-modal>

        <b-modal ref="modalUpdate" centered @hide="resetUpdateModal" title="Update employee ...">
            <b-container style="padding-bottom: 10%;">
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">First Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="updatedEmployee.firstName" type="text" placeholder="Enter first name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Last Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="updatedEmployee.lastName" type="text" placeholder="Enter last name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Salary</label></b-col>
                    <b-col>
                        <b-form-input v-model="updatedEmployee.salary" type="text" placeholder="Enter salary"></b-form-input>
                    </b-col>
                </b-row>
            </b-container>
            <div slot="modal-footer" class="w-100">
                <b-btn size="sm" class="float-right" variant="warning" @click="updateEmployee">
                    Update
                </b-btn>
            </div>
        </b-modal>

        <b-modal ref="modalDelete" centered @hide="resetDeleteModal" title="Delete employee ...">
            <b-container style="padding-bottom: 10%;">
                <b-row class="mb-1">
                    <p>Do you really want to delete employee?</p>
                </b-row>
            </b-container>
            <div slot="modal-footer" class="w-100">
                <b-btn size="sm" class="float-right" variant="danger" @click="deleteEmployee">
                    Delete
                </b-btn>
            </div>
        </b-modal>
    </div>
</template>

<script>
    export default {

        data () {
            return {
                loading: true,
                sortBy: null,
                sortDesc: false,
                sortDirection: 'asc',
                striped: true,
                fields: [
                    { key: 'id', sortable: true },
                    { key: 'firstName', sortable: true },
                    { key: 'lastName', sortable: true },
                    { key: 'salary', sortable: true },
                    { key: 'actions', sortable: false, label: 'Actions', class: 'actions-col'}
                ],
                currentPage: 1,
                size: 10,
                totalPages: 1,
                employees: [],
                updatedEmployee: { id: '', firstName: '', lastName: '', salary: ''},
                newEmployee: { id: null, firstName: '', lastName: '', salary: ''},
                deletableEmployee: { id: null, firstName: '', lastName: '', salary: ''}
            }
        },

        beforeRouteUpdate (to, from, next) {

            // обрабатываем изменение параметров маршрута ...
            let page = to.query.page;
            let size = to.query.size;

            if (size !== null && size !== '' && !isNaN(size) && size > 0 && size <= 30) {
                this.size = parseInt(size)
            }
            else {
                this.size = 10
            }

            if (page !== null && page !== '' && !isNaN(page) && page > 0) {
                this.currentPage = parseInt(page)
            }
            else {
                this.currentPage = 1
            }

            //обновляем DOM ...
            this.getEmployees()

            // не забываем вызвать next()
            next()
        },

        watch: {

            size: function (newSize, oldSize) {

                if (newSize !== null && newSize !== '' && !isNaN(newSize) && newSize > 0 && newSize <= 30) {

                    this.size = newSize;

                    let criteria = { page: this.currentPage, size: this.size}

                    this.$router.push({ path: '/', query: criteria });
                }
            }
        },

        mounted() {

            let page = this.$route.query.page;
            let size = this.$route.query.size;

            if (size !== null && size !== '' && !isNaN(size) && size > 0 && size <= 30) this.size = size;
            if (page !== null && page !== '' && !isNaN(page) && page > 0) this.currentPage = page;

            this.getEmployees()
        },

        methods: {

            getEmployees() {

                let criteria = {
                    size: this.size,
                    page: this.currentPage
                };

                this.$axios
                    .post('/employees', criteria)
                    .then(response => {

                        this.employees = response.data.employees;
                        this.totalPages = response.data.totalPages;
                        this.size = response.data.size;
                        this.currentPage = response.data.page;

                        this.loading = false;
                    })
                    .catch(e => { console.log(e) })
            },

            linkGen(pageNum) {

                const that = this;

                return {
                    path: '/',
                    query: { page : pageNum, size: that.size }
                }
            },

            showAddModal() {
                this.$refs.modalAdd.show()
            },

            resetAddModal () {
                this.newEmployee = { id: null, firstName: '', lastName: '', salary: ''}
            },

            addEmployee () {

                this.$axios
                    .post("/employee/add", this.newEmployee)
                    .then(() => {

                        this.$refs.modalAdd.hide();

                        this.getEmployees()
                    })
                    .catch(e => { console.log(e) })
            },

            showUpdateModal(item) {
                this.updatedEmployee.id = item.id;
                this.updatedEmployee.firstName = item.firstName;
                this.updatedEmployee.lastName = item.lastName;
                this.updatedEmployee.salary = item.salary;

                this.$refs.modalUpdate.show()
            },

            resetUpdateModal () {
                this.updatedEmployee = { id: '', firstName: '', lastName: '', salary: ''}
            },

            updateEmployee () {

                this.$axios
                    .put("/employee/update", this.updatedEmployee)
                    .then(() => {

                        this.$refs.modalUpdate.hide();

                        this.getEmployees()
                    })
                    .catch(e => { console.log(e) })
            },

            showDeleteModal(item) {
                this.deletableEmployee.id = item.id;
                this.deletableEmployee.firstName = item.firstName;
                this.deletableEmployee.lastName = item.lastName;
                this.deletableEmployee.salary = item.salary;

                this.$refs.modalDelete.show()
            },

            resetDeleteModal () {
                this.updatedEmployee = { id: null, firstName: '', lastName: '', salary: ''}
            },

            deleteEmployee () {

                this.$axios
                    .delete("/employee/delete/" + this.deletableEmployee.id)
                    .then(() => {

                        this.$refs.modalDelete.hide();

                        this.getEmployees();
                    })
                    .catch(e => { console.log(e) })
            }
        }
    }
</script>

<style scoped>
    .mt-hr {
        margin-top: 0.4rem;
    }
    .w-3rem {
        width: 3rem;
    }
</style>

<style>
    .actions-col {
        width: 11rem;
    }
    .pagination {
        margin-bottom: 0 !important;
    }
</style>