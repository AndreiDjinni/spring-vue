<template>
    <div class="container">
        <div class="mx-auto w-50">
            <div class="card my-5">
                <div class="card-body">
                    <div class="rounded-circle user-icon-fone my-3 mx-auto">
                        <icon class="user-icon" name="user"></icon>
                    </div>
                    <h5 class="card-title text-center my-3">Employee info</h5>
                    <div class="w-100">
                        <div class="w-25 ml-5 d-inline-block">
                            <p>First name</p>
                        </div>
                        <div class="w-50 ml-5 d-inline-block">
                            <p>{{ employee.firstName }}</p>
                        </div>
                    </div>
                    <div class="w-100">
                        <div class="w-25 ml-5 d-inline-block">
                            <p>Last name</p>
                        </div>
                        <div class="w-50 ml-5 d-inline-block">
                            <p>{{ employee.lastName }}</p>
                        </div>
                    </div>
                    <div class="w-100">
                        <div class="w-25 ml-5 d-inline-block">
                            <p>Salary</p>
                        </div>
                        <div class="w-50 ml-5 d-inline-block">
                            <p>{{ employee.salary }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Employee",

        data () {
            return {
                loading: true,
                employee: null
            }
        },

        mounted() {
            this.getEmployees()
        },

        methods: {

            getEmployees() {

                this.$axios
                    .get('/employee/' + this.$route.params.id)
                    .then(response => {

                        this.employee = response.data;
                        this.loading = false
                    })
                    .catch(e => { console.log(e) })
            }
        }
    }
</script>

<style scoped>
    .user-icon-fone {
        width: 10rem;
        height: 10rem;
        background-color: aliceblue;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
    }

    .user-icon {
        color: white;
        width: 6rem;
        height: 6rem;
        cursor: pointer;
    }
</style>