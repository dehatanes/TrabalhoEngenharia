// Login
$(function() {
	if (!$('#login-form').length) {
        return false;
    }

    var loginValidationSettings = {
	    rules: {
	        username: {
	            required: true,
	            email: true
	        },
	        password: "required",
	        agree: "required"
	    },
	    messages: {
	        username: {
	            required: "Por favor, digite um usuário",
	            email: "Por favor, digite um endereço de e-mail válido!"
	        },
	        password:  "Por favor, digite sua senha",
	        agree: "Por favor, aceite nossos termos de compromisso"
	    },
	    invalidHandler: function() {
			animate({
				name: 'shake',
				selector: '.auth-container > .card'
			});
		}
	}

	$.extend(loginValidationSettings, config.validations);

    $('#login-form').validate(loginValidationSettings);
})

// http://codeheaven.io/how-to-use-axios-as-your-http-client-pt/
// https://pt.stackoverflow.com/questions/102125/como-fazer-que-ao-clicar-numa-tecla-um-submit-%C3%A9-enviado
sessionStorage.removeItem ("usuario")
sessionStorage.setItem ("usuario", valor)
sessionStorage.getItem ("usuario")
$('#nomedoid').val()