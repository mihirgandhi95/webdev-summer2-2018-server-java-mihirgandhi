function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.findUserByUserName = findUserByUserName;
    this.registerUser = registerUser;
    this.getProfile = getProfile;
    this.updateProfile = updateProfile;
    this.logout = logout;
    this.login = login;
    this.url1 = '/api/register';
    this.url2 = '/api/profile';
    this.url3 = '/api/login';
    this.url4 = '/api/logout';

    var self = this;
    this.url = '/api/user';


    function findAllUsers() {
        var promise = fetch(this.url);
        return promise.then(function (response) {
            return response.json();
        });
    }


    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });

    }


    function updateUser(user, userId, callback) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(callback);
    }



    function createUser(user,callback) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(callback);
    }


    function deleteUser(userId, callback){
        return fetch(self.url+ '/'+userId,
            {
                method: 'delete'
            }).then(callback);
    }


    function findUserByUserName(username){
        return fetch(self.url1 + "/" + username).then(function(response){return response.json();});
    }

    function registerUser(user){
        return fetch ('/api/register',
            {
                method: 'post',
                body: JSON.stringify(user),
                headers: {'content-Type': 'application/json'

                }
            }).then(function(response){return response.json();});
    }



    function updateProfile(user){
        return fetch(self.url2, {
            method: 'put',
            body: JSON.stringify(user),
            headers:{ 'content-Type':'application/json'
    }

        }).then(function(response){return response.json();});
    }






    function getProfile()
    {
        return fetch('/api/getProfile').then(
            function(response)
               {

                 return response.json();
               });
    }



    function login(user){
        return fetch(self.url3,{
            method: 'post',
            body: JSON.stringify(user),
            headers: {'content-type': 'application/json'}


        }).then(function (response) {return response.json();});
    }


    function logout(user){

        var response = $.ajax({
            async:false,
            type: "POST",
            contentType:"application/json;charset= utf-8",
            dataType: 'json',
            url  : "/api/logout",
            data : JSON.stringify(user),



        });
        return response.JSON

    }



}





