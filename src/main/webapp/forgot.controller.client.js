$(document).ready(function(){

    $(main);

    function main() {


        $(document).on('click','#SubmitBtn',function() {
            alert('An email has been sent to you at your email ID! ');
            window.location.replace("/jquery/components/login/login.template.client.html");

        });




    }
});