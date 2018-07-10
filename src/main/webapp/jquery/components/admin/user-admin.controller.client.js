//IIFE
(function () {

    var tbody;
    var template;
    var userService = new UserServiceClient();
    var username;
    var password;
    var firstName;
    var lastName;
    var email;
    var role;
    var phone;
    var dateOfBirth;
    var userId;


    $(document).ready(main);


    function main() {
        findAllUsers();
        $('#button2').hide();
        tbody = $('tbody');
        template = $('.template');
        $('#button1').click(createUser);
        $(document).on('click','#button3',function(){findUserById(this);});
    }



    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var phone = $('#phoneFld').val();
        var email = $('#emailFld').val();
        var dateOfBirth = $('#dateOfBirthFld').val();


        var newUserObj  = new User(username,password,firstName,lastName,role,phone, email,dateOfBirth);

        userService
            .createUser(newUserObj, findAllUsers());
        alert('created user!')
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#roleFld').val('');
        $('#phoneFld').val('');
        $('#emailFld').val('');
        $('#dateOfBirthFld').val('');

    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id);
            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);
            clone.find('.username')
                .html(user.username);
            clone.find('.password').html(user.password);
            clone.find('.firstName').html(user.firstName);
            clone.find('.lastName').html(user.lastName);
            clone.find('.role').html(user.role);
            clone.find('.phone').html(user.phone);
            clone.find('.email').html(user.email);
            clone.find('.dateOfBirth').html(dateOfBirth);
            tbody.append(clone);
        }
    }


    function findUserById(userId){

        userService
            .findUserById(userId)
            .then(renderUser);
    }



    function renderUser(user){
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#roleFld').val(user.role);
        $('#phoneFld').val(user.phone);
        $('#emailFld').val(user.email);
        $('#dateOfBirthFld').val(user.dateOfBirth);
        $('#button1').hide();
        $('#button2').show();
        alert("Click on update to complete making changes");
        $(document).on('click','#button2',function(){updateUser(user);});

    }


    function updateUser(user){
        user.firstName = $('#firstNameFld').val('');
        user.lastName = $('#lastNameFld').val('');
        user.username = $('#usernameFld').val('');
        user.password = $('#passwordFld').val('');
        user.role = $('#roleFld').val('');
        user.email = $('#emailFld').val('');
        user.phone = $('#phoenFld').val('');
        user.dateOfBirth = $('#dateOfBirthFld').val('');
        userService.updateUser(user, user.id, findAllUsers());
        alert('updated user');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#roleFld').val('');
        $('#emailFld').val('');
        $('#phoenFld').val('');
        $('#dateOfBirthFld').val('');

    }



    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId,findAllUsers);

        alert('deleted user!');

    }



    function editUser(event) {
        console.log('editUser');
        console.log(event);
    }

})();