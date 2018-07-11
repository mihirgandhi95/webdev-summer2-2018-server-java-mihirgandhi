function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.findUserByUserName = findUserByUserName;
    this.registerUser = registerUser;
    this.url1 = '/api/register';

    var self = this;
    this.url = 'http://localhost:8080/api/user';


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




}





