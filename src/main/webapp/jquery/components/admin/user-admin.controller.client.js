(function () {

    var username;
    var password;
    var firstName;
    var lastName;
    var userService  = new UserServiceClient();


    var template;
    var tbody;
    this.url = '/api/user';
    var self = this;


    jQuery(main);

    function main(){
        findAllUsers();
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);
        var tr1 = template.clone();
        tbody.append(tr1);
        $(document).on('click','#editUser',function(){getUserByEvent(this);});
        $(document).on('click', "#deleteUser", deleteUser);

    }







    //creation of user
    function createUser() {
        console.log('createUser');

        username = $('#usernameFld').val();
        password = $('#passwordFld').val();
        firstName = $('#firstNameFld').val();
        lastName = $('#lastNameFld').val();
        role = $("#roleFld").val();
        phone = $("#phoneFld").val();
        email = $("#emailFld").val();
        dateOfBirth = $("#dateOfBirthFld").val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role,
            phone: phone,
            email:email,
            dateOfBirth:dateOfBirth

        };

        //call the userService
        userService
            .createUser(user,findAllUsers);

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








    //render the users on the screen
    function renderUsers(users)
    {
      for(var i =0 ; i<users.length; i++)
      {
         // tbody = $('tbody');
         // template = $('.template');
          var user=users[i];
          console.log(user);
          tbody.empty();
          for(var i=0; i<users.length; i++) {
              var user = users[i];
              var clone = template.clone();
              clone.attr('id', user.id);
             // clone.find('.delete').click(deleteUser);
             // clone.find('.edit').click(editUser);
              clone.find('.username').html(user.username);
              clone.find('.password').html(user.password);
              clone.find('.firstName').html(user.firstName);
              clone.find('.lastName').html(user.lastName);
              clone.find('.role').html(user.role);
              clone.find('.phone').html(user.phone);
              clone.find('.email').html(user.email);
              clone.find('.dateOfBirth').html(user.dateOfBirth);
              tbody.append(clone);
          }
      }
    }




    //find all the users
    function findAllUsers(){
        userService.findAllUsers().then(renderUsers);

    }



    //get user from event like mouseclick
    function getUserByEvent(event)
    {
        var userId = $(event).closest('tr').attr('id');
        userService.findUserById(userId).then(renderUser);
    }


    //render a single user
    function renderUser(user){
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#roleFld').val(user.role);
        $('#phoneFld').val(user.phone);
        $('#emailFld').val(user.email);
        $('#dateOfBirthFld').val(user.dateOfBirth);
        $('#createUser').hide();
        $('#updateUser').show();
        alert("Click on update to complete making changes");
        $(document).on('click','#updateUser',function(){updateUser(user);});
    }




    //find users by their id
    function findUserById(userId){
        userService
            .findUserById(userId)
            .then(renderUser);
    }


    //update the data of the users
    function updateUser(user){
        user.firstName = $('#firstNameFld').val();
        user.lastName = $('#lastNameFld').val();
        user.username = $('#usernameFld').val();
        user.password = $('#passwordFld').val();
        user.role = $('#roleFld').val();
        user.email = $('#emailFld').val();
        user.phone = $('#phoenFld').val();
        user.dateOfBirth = $('#dateOfBirthFld').val();
        userService.updateUser(user, user.id, findAllUsers);
        alert('updated user');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#roleFld').val('');
        $('#emailFld').val('');
        $('#phoenFld').val('');
        $('#dateOfBirthFld').val('');
        $('#updateUser').hide();
        $('#createUser').show();
    }


    //delete the user
    function deleteUser(event)
    {
        var deleteEvent = $(event.currentTarget);
        var userId= deleteEvent.parent().parent().attr('id');
        userService.deleteUser(userId,findAllUsers);
        alert('deleted the user! ');

    }








})();