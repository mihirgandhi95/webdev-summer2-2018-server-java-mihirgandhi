function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;


    this.url = '/api/user';
    var self = this;




    function createUser(user, callback) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(callback);
    }





    function findAllUsers(callback) {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }



    function findUserById(userId, callback) {
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




    function deleteUser(userId, callback) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        }).then(callback);
    }

    
}
