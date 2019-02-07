(function () {

    $(document).ready(function () {
        // $(":range").rangeinput({progress: true});
        //

        /* Slide  */
        $("ul.quesa_mod  li#question_mod_a a").click(function () {

            $(this).parents().find("ul.quesa_mod>#answer_mod_a").slideToggle();

        });
        $("ul.quesa_mod  li#question_mod_b a").click(function () {

            $(this).parents().find("ul.quesa_mod>#answer_mod_b").slideToggle();

        });

    });
})()