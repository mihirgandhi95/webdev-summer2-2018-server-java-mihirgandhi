$(document).ready(function(){

    $(main);

    function main() {


        $(document).on('click','#userAdminPage',function() {
            window.location.replace("/jquery/components/admin/user-admin.template.client.html");
        });

        $(document).on('click','#LoginPage',function() {
            window.location.replace("/jquery/components/login/login.template.client.html");
        });

        $(document).on('click','#registerPageLink',function() {
            window.location.replace("/jquery/register/register.template.client.html");
        });

        $(document).on('click','#ProfilePage',function() {
            window.location.replace("/jquery/components/profile/profile.template.client.html");
        });





    }
});