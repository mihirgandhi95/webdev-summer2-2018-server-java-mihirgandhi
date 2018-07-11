$(document).ready(function(){



    var userService = new UserServiceClient();
    var username;
    var password;
    var firstName;
    var lastName;
    var email;
    var role;
    var phone;
    var dateOfBirth;
    var res;


    $(main);

    function main() {
        getProfileData();
        $(document).on('click','#ProfileUpdtBtn', function(){updateProfile(res);});
        $(document).on('click','#logoutBtn',function() {
            userService.logout(res);

                window.location.replace("../login/login.template.client.html");

        });

    }


    function updateProfile(user){
        username = $('#username').val();
        password = $('#password').val();
        firstName = $('#firstName').val();
        lastName = $('#lastName').val();
        role = $("#role").val();
        phone = $("#phone").val();
        email = $("#email").val();
        dateOfBirth = $("#dateOfBirth").val();


    var newUser = {
        username: username,
        password: password,
        firstName: firstName,
        lastName: lastName,
        role: role,
        phone: phone,
        email:email,
        dateOfBirth:dateOfBirth

    };


    var response = userService.updateProfile(newUser).then(function(results)
    {
        var res= results;

    });
    }


    function getProfileData(){
    var currentUser  = userService.getProfile().then(function (results)
    {

        res = results;
        $('#username').val(res.username);
        $('#password').val(res.password);
        $('#firstName').val(res.firstName);
        $('#lastName').val(res.lastName);
        $("#role").val(res.role);
        $("#phone").val(res.phone);
        $("#email").val(res.email);
        $("#dateOfBirth").val(res.dateOfBirth);



    });


}

});



