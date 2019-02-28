<template>
    <div v-if="!loading">

        <div class="w-100 my-2 d-flex">
            <div class="w-25">
                <b-form-input v-model="criteria.size" class="w-3rem" size="sm" type="text" placeholder="Enter size" />
            </div>
            <div class="w-50">
                <b-pagination-nav v-model="criteria.page" size="sm" :use-router="true" align="center"
                                  :link-gen="linkGen" :number-of-pages="criteria.totalPages" />
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
            <template slot="lastName" slot-scope="data">
                {{data.item.lastName}}
            </template>
            <template slot="salary" slot-scope="data">
                {{data.item.salary}}
            </template>
            <template slot="active" slot-scope="data">
                {{data.item.active}}
            </template>
            <template slot="department" slot-scope="data">
                {{data.item.departmentName}}
            </template>
            <template slot="actions" slot-scope="data">
                <div style="display: flex; justify-content: space-between;">
                    <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button size="sm" @click.stop="showUser(data.item)" variant="primary" class="mr-1" style="width: 4rem">
                        View
                    </b-button>
                    <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button size="sm" @click.stop="showUpdateModal(data.item)" variant="warning" class="mr-1" style="width: 4rem">
                        Update
                    </b-button>
                    <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
                    <b-button size="sm" @click.stop="showDeleteModal(data.item)" variant="danger" class="mr-1" style="width: 4rem">
                        Delete
                    </b-button>
                </div>
            </template>
        </b-table>

        <div class="w-100 my-2 d-flex">
            <div class="w-25">
                <b-form-input v-model="criteria.size" class="w-3rem" size="sm" type="text" placeholder="Enter size" />
            </div>
            <div class="w-50">
                <b-pagination-nav v-model="criteria.page" size="sm" :use-router="true" align="center"
                                  :link-gen="linkGen" :number-of-pages="criteria.totalPages" />
            </div>
            <div class="w-25">
                <b-button size="sm" @click="showAddModal" class="float-right" variant="success">
                    Add employee
                </b-button>
            </div>
        </div>

        <b-modal ref="modalAdd" centered @hide="resetAddModal" title="Add employee ...">
            <b-container style="padding-bottom: 10%;">
                <b-row v-if="errors.firstName" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Enter First Name, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">First Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="newEmployee.firstName" type="text" placeholder="Enter first name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row v-if="errors.lastName" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Enter Last Name, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Last Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="newEmployee.lastName" type="text" placeholder="Enter last name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row v-if="errors.salary" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Enter Salary, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Salary</label></b-col>
                    <b-col>
                        <b-form-input v-model="newEmployee.salary" type="text" placeholder="Enter salary"></b-form-input>
                    </b-col>
                </b-row>
                <b-row v-if="errors.active" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Select Active, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Active</label></b-col>
                    <b-col>
                        <b-form-select v-model="newEmployee.active" :options="activeOptions">
                            <template slot="first">
                                <option :value="null" disabled>-- Select --</option>
                            </template>
                        </b-form-select>
                    </b-col>
                </b-row>
                <b-row v-if="errors.departmentId" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Select Department, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Department</label></b-col>
                    <b-col>
                        <b-form-select v-model="newEmployee.departmentId" :options="departmentOptions">
                            <template slot="first">
                                <option :value="null" disabled>-- Select department --</option>
                            </template>
                        </b-form-select>
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
                <b-row v-if="errors.firstName" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Enter First Name, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">First Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="updatedEmployee.firstName" type="text" placeholder="Enter first name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row v-if="errors.lastName" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Enter Last Name, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Last Name</label></b-col>
                    <b-col>
                        <b-form-input v-model="updatedEmployee.lastName" type="text" placeholder="Enter last name"></b-form-input>
                    </b-col>
                </b-row>
                <b-row v-if="errors.salary" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Enter Salary, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Salary</label></b-col>
                    <b-col>
                        <b-form-input v-model="updatedEmployee.salary" type="text" placeholder="Enter salary"></b-form-input>
                    </b-col>
                </b-row>
                <b-row v-if="errors.active" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Select Active, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Active</label></b-col>
                    <b-col>
                        <b-form-select v-model="updatedEmployee.active" :options="activeOptions">
                            <template slot="first">
                                <option :value="null" disabled>-- Select --</option>
                            </template>
                        </b-form-select>
                    </b-col>
                </b-row>
                <b-row v-if="errors.departmentId" class="error">
                    <b-col cols="4"></b-col>
                    <b-col>
                        <i class="error-text">Select Department, please ...</i>
                    </b-col>
                </b-row>
                <b-row class="mb-1">
                    <b-col cols="4"><label class="mt-hr">Department</label></b-col>
                    <b-col>
                        <b-form-select v-model="updatedEmployee.departmentId" :options="departmentOptions">
                            <template slot="first">
                                <option :value="null" disabled>-- Select department --</option>
                            </template>
                        </b-form-select>
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
                    { key: 'active', sortable: true },
                    { key: 'department', sortable: true },
                    { key: 'actions', sortable: false, label: 'Actions', class: 'actions-col'}
                ],
                criteria: {
                    size: 10,
                    page: 1,
                    search: '',
                    totalPages: 1
                },
                employees: [],
                departments: [],
                activeOptions: [
                    { text: 'Yes', value: 'true' },
                    { text: 'No', value: 'false' }
                ],
                updatedEmployee: { id: null, firstName: '', lastName: '', salary: '', active: null, departmentId: null, departmentName: null},
                newEmployee: { id: null, firstName: '', lastName: '', salary: '', active: null, departmentId: null, departmentName: null},
                deletableEmployee: { id: null, firstName: '', lastName: '', salary: '', active: null, departmentId: null, departmentName: null},
                errors: {
                    firstName: false,
                    lastName: false,
                    salary: false,
                    active: false,
                    departmentId: false
                }
            }
        },

        beforeRouteUpdate (to, from, next) {

            // обрабатываем изменение параметров маршрута ...
            let page = to.query.page;
            let size = to.query.size;

            if (size !== null && size !== '' && !isNaN(size) && size > 0 && size <= 30) this.criteria.size = parseInt(size);
            else this.criteria.size = 10;

            if (page !== null && page !== '' && !isNaN(page) && page > 0) this.criteria.page = parseInt(page);
            else this.criteria.page = 1;

            this.criteria.search = to.query.search;

            //обновляем DOM ...
            this.getEmployees()

            // не забываем вызвать next()
            next()
        },

        watch: {

            'criteria.size' : function (newSize, oldSize) {

                if (newSize !== null && newSize !== '' && !isNaN(newSize) && newSize > 0 && newSize <= 30) {

                    this.criteria.size = newSize;

                    let criteria = { page: this.criteria.page, size: this.criteria.size, search: this.$route.query.search};

                    this.$router.push({ path: '/', query: criteria });
                }
            }
        },

        computed: {

            departmentOptions: function () {
                let options = [];

                for (let i = 0; i < this.departments.length; i++) {

                    let department = this.departments[i];

                    options.push({ value: department.id, text: department.name })
                }

                return options;
            }
        },

        mounted() {

            let page = this.$route.query.page;
            let size = this.$route.query.size;
            let search = this.$route.query.search;

            if (size !== null && size !== '' && !isNaN(size) && size > 0 && size <= 30) this.criteria.size = size;
            if (page !== null && page !== '' && !isNaN(page) && page > 0) this.criteria.page = page;
            this.criteria.search = search;

            this.getEmployees()
        },

        methods: {

            getEmployees() {

                let criteria = {
                    size: this.criteria.size,
                    page: this.criteria.page,
                    search: this.criteria.search
                };

                this.$axios
                    .post('/employees', criteria)
                    .then(response => {

                        this.employees = response.data.employees;
                        this.criteria.totalPages = response.data.totalPages;
                        this.criteria.size = response.data.size;
                        this.criteria.page = response.data.page;

                        this.loading = false;
                    })
                    .catch(e => { console.log(e) })
            },

            getDepartments() {

                this.$axios
                    .get('/departments')
                    .then(response => { this.departments = response.data; })
                    .catch(e => { console.log(e) })
            },

            linkGen(pageNum) {

                const that = this;

                return {
                    path: '/',
                    query: { page : pageNum, size: that.criteria.size, search: this.$route.query.search }
                }
            },

            showAddModal() {

                if (this.departments.length === 0) this.getDepartments();

                this.$refs.modalAdd.show()
            },

            resetAddModal () {

                this.resetErrors();

                this.newEmployee = { id: null, firstName: '', lastName: '', salary: '', active: null, departmentId: null, departmentName: null }
            },

            showUser (employee) {
                this.$router.push({ name: 'employee', params: { id: employee.id }})
            },

            addEmployee () {

                const that = this;

                this.$axios
                    .post("/employee/add", this.newEmployee)
                    .then(() => {

                        this.$refs.modalAdd.hide();

                        this.getEmployees()
                    })
                    .catch(e => {

                        console.log(e);

                        that.resetErrors();

                        e.response.data.errors.forEach(function(element) {
                            that.errors[element.field] = true;
                        });
                    })
            },

            showUpdateModal(item) {

                if (this.departments.length === 0) this.getDepartments();

                this.updatedEmployee.id = item.id;
                this.updatedEmployee.firstName = item.firstName;
                this.updatedEmployee.lastName = item.lastName;
                this.updatedEmployee.salary = item.salary;
                this.updatedEmployee.active = item.active;
                this.updatedEmployee.departmentId = item.departmentId;
                this.updatedEmployee.departmentName = item.departmentName;

                this.$refs.modalUpdate.show()
            },

            resetUpdateModal () {

                this.resetErrors();

                this.updatedEmployee = { id: null, firstName: '', lastName: '', salary: '', active: null, departmentId: null, departmentName: null}
            },

            updateEmployee () {

                const that = this;

                this.$axios
                    .put("/employee/update", this.updatedEmployee)
                    .then(() => {

                        this.$refs.modalUpdate.hide();

                        this.getEmployees()
                    })
                    .catch(e => {

                        console.log(e);

                        that.resetErrors();

                        e.response.data.errors.forEach(function(element) {
                            that.errors[element.field] = true;
                        });
                    })
            },

            showDeleteModal(item) {
                this.deletableEmployee.id = item.id;
                this.deletableEmployee.firstName = item.firstName;
                this.deletableEmployee.lastName = item.lastName;
                this.deletableEmployee.salary = item.salary;
                this.deletableEmployee.active = item.active;
                this.deletableEmployee.departmentId = item.departmentId;
                this.deletableEmployee.departmentName = item.departmentName;

                this.$refs.modalDelete.show()
            },

            resetDeleteModal () {
                this.updatedEmployee = { id: null, firstName: '', lastName: '', salary: '', active: null, departmentId: null, departmentName: null }
            },

            deleteEmployee () {

                this.$axios
                    .delete("/employee/delete/" + this.deletableEmployee.id)
                    .then(() => {

                        this.$refs.modalDelete.hide();

                        this.getEmployees();
                    })
                    .catch(e => { console.log(e) })
            },

            resetErrors () {
                for (let key in this.errors) {
                    this.errors[key] = false;
                }
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