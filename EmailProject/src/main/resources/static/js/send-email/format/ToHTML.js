export default class ToHTML{

    static emailTemplatesToHTML(emailTemplate){
        return '<option name="email-template" value="'+emailTemplate+'">'+emailTemplate+'</option>'
    }

    static subjectToHTML(subject){
        return '<input id="email-subject" type="text" value="'+subject+'" class="col">';
    }

    static selectAllToHTML(check){
        return '<label for="select-all-accounts-checkbox">selecionar todos</label>\n'
        +'<input id="select-all-accounts-checkbox" '+check+' type="checkbox"/>';
    }

    static accountToHTML(cpf,name,email,check){
        return '<tr name="list-of-accounts">\n'
            +'<td><input name="accountCpf" value="'+cpf+'" '+check+' type="checkbox"></input></td>\n'
            +'<td name="name" value="'+name+'">'+name+'</td>\n' 
            +'<td name="cpf" value="'+cpf+'">'+cpf+'</td>\n'
            +'<td name="email" value="'+email+'">'+email+'</td>\n'   
        +'</tr>\n';
    }

}