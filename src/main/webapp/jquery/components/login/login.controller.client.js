$(document).ready(function(){
    var userService = new UserServiceClient();


    $(main);


    function main(){

        $(document).on('click','#forgotPassword',function() {
            window.location.replace("../../../forgot.template.client.html");
        });


        $(document).on('click',"#login-button", function()
        {
            var username = $("#login-username").val();
            var password = $("#login-password").val();
            login(username,password);


        });


        $(document).on('click',"#signup",function() {
            window.location= "../../register/register.template.client.html"
        });
    }


    function login(username,password){

        var userObj = {
            username: username,
            password: password,
            firstName: null,
            lastName: null,
            role: null,
            phone: null,
            email:null,
            dateOfBirth:null

        };

        var validation = userService.login(userObj).then(function(results){
            var response =results;
            if(response.username== null)
            {
                var response = userService.findUserByUserName(username).then(function(results){
                    var res = results;
                    if(res.bool == "true")
                    {
                        alert('incorrect password');
                    }
                    else{
                        alert('username does not exist');
                    }

                });
            }
            else{
                alert("hello," +" " + response.username)
                window.location = "../profile/profile.template.client.html";
            }
        });
        $("#login-username").val("");
        $("#login-password").val("");

    }
});