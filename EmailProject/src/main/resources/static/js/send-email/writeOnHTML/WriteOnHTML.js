
import ToHTML from "../format/ToHTML.js";

export default class WriteOnHTML{

    static printOnEmailTemplates(arrayOfEmailTemplates, emailTemplatesId){
        $(emailTemplatesId).empty();
        $.each(arrayOfEmailTemplates, (indexInArray, valueOfElement)=>{
            let write=ToHTML.emailTemplatesToHTML (valueOfElement);
            console.log(write);
            $(emailTemplatesId).append(write);
        });
    }

    static printOnSubject(subject, subjetcId){
        $(subjetcId).empty();
        let write=ToHTML.subjectToHTML(subject);
        console.log(write);
        $(subjetcId).append(write);
    }

    static printOnSelectAll(check){
        $('#select-all-accounts-div').empty();
        $('#select-all-accounts-div').append(ToHTML.selectAllToHTML(check));
    }

    static printOnTable(arrayOfAccountsDTO, tableBodyId) {
        $(tableBodyId).empty();
        $.each(arrayOfAccountsDTO, (indexInArray, valueOfElement)=>{
            let write=ToHTML.accountToHTML (valueOfElement.cpf, valueOfElement.name, valueOfElement.email, valueOfElement.check);
            //console.log(write);
            $(tableBodyId).append(write);
        });
        
    }


}