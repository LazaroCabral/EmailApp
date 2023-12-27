export default class AccountDTO{
    constructor(cpf, name, email, check){
        this.cpf=cpf;
        this.name=name;
        this.email=email;
        this.check=check==true ? 'checked':'';
    }

    static DOMAccountsToAccountDTOs(jqueryAccountsDOM, check) {
        let accountsDTOs=[]
        $.each(jqueryAccountsDOM, function (indexInArray, valueOfElement) { 
            
            let cpf=valueOfElement.children.cpf.innerHTML;
            let name=valueOfElement.children.name.innerHTML;
            let email=valueOfElement.children.email.innerHTML;

            accountsDTOs.push(
                new AccountDTO(cpf, name, email, check));
        });
        return accountsDTOs;
    }
}