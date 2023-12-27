export class AccountsPOSTDTO{

    /*constructor(selectAll){
        this.selectAll=selectAll;
    }*/

    constructor (selectedAccounts, selectedAccountsNOT, selectAll,
        emailTemplate, subject){
        
            this.selectedAccounts=selectedAccounts,
            this.selectedAccountsNOT=selectedAccountsNOT,
            this.selectAll=selectAll,
            this.emailTemplate=emailTemplate,
            this.subject=subject

    }
    
}