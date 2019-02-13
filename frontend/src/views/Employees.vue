<template>
    <div>
        <div class="add-employee-row">
            <b-button size="sm" @click="showAddModal" class="my-1" variant="success">
                Add employee
            </b-button>
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
                employees: [],
                updatedEmployee: { id: '', firstName: '', lastName: '', salary: ''},
                newEmployee: { id: null, firstName: '', lastName: '', salary: ''},
                deletableEmployee: { id: null, firstName: '', lastName: '', salary: ''}
            }
        },
        mounted() {
            this.$axios
                .get('/employees')
                .then(response => { this.employees = response.data})
                .catch(e => {
                    console.log(e)
                })
        },
        methods: {
            showAddModal() {
                this.$refs.modalAdd.show()
            },
            resetAddModal () {
                this.newEmployee = { id: null, firstName: '', lastName: '', salary: ''}
            },
            addEmployee () {
                const that = this;

                this.$axios
                    .post("/employee/add", this.newEmployee)
                    .then(response => {
                        this.$refs.modalAdd.hide();
                        that.employees = response.data
                    })
                    .catch(e => {
                        console.log(e)
                    })
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
                const that = this;
                this.$axios
                    .put("/employee/update", this.updatedEmployee)
                    .then(response => {
                        this.$refs.modalUpdate.hide();
                        that.employees = response.data
                    })
                    .catch(e => {
                        console.log(e)
                    })
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
                const that = this;

                this.$axios
                    .delete("/employee/delete/" + this.deletableEmployee.id)
                    .then(response => {
                        this.$refs.modalDelete.hide();
                        that.employees = response.data
                    })
                    .catch(e => {
                        console.log(e)
                    })
            }
        }
    }
</script>

<style scoped>
    .add-employee-row {
        display: flex;
        justify-content: flex-end;
    }
    .mt-hr {
        margin-top: 0.4rem;
    }
</style>

<style>
    .actions-col {
        width: 11rem;
    }
</style>