// IIFE
// Immediately Invoked Function Expression
$(document).ready(function () {

    var userService = new UserServiceClient();

    $(main);


    function main()
    {
        $("#username").blur(findUserByUserName);
        $(document).on('click','#registerBtn', function () {registerUser();});
    }

    function findUserByUserName(){
        var userName = $("#username").val();


        if(userName == "")
        {
            $('#registerBtn').attr('disabled',true);
            alert("Registration failed!");

        }

        else {
            var response = userService.findUserByUserName(userName).then(function(results){
            var res = results;
            if(res.exists == 'true')

            {
                $('#registerBtn').attr('disabled',true);
                alert('registration faiiled');

            }
            });
        }

    }

    function registerUser()
    {
         var pass2 = $("#password2").val();
         var pass = $("#password").val();
         var user = $("#username").val();


         var userObj = {
            username: user,
            password: pass,
            firstName: null,
            lastName: null,
            role: null,
            phone: null,
            email:null,
            dateOfBirth:null

        };


         if(pass2 != password){

         if(pass2 =="")
         {
             alert("registration failed, password validation field can't be empty");
             $("#username").val('');
             $("#password").val('');
             $("#password2").val('');

         }

         else if (pass=""){
             alert("registration failed, password field can't be empty");
             $("#username").val('');
             $("#password").val('');
             $("#password2").val('');
         }

         else {
             alert("registration failed passwords not same");
             $("#username").val('');
             $("#password").val('');
             $("#password2").val('');
         }

         }
         else
         {
             var response = userService.register(user).then(function (results){
                 var res = results;
                 window.location= "/jQuery/components/profile/profile.template.client.html";
                 $("#username").val('');
                 $("#password").val('');
                 $("#password2").val('');


             })
         }



    }


});