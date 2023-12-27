import { AccountsPOSTDTO } from "../Accounts/DTOs/AccountsPOSTDTO.js";

export class SubmitAccounts{

    constructor(url) {
        this.url=url;
    }
    
    static async accountsPOST(url, accountsPOSTDTO) {
        let data=JSON.stringify(accountsPOSTDTO);
        selectedAccountsManager.selectedAccounts
        
        await $.$.ajax({
            type: "POST",
            url: url,
            data: accountsPOSTDTO,
            dataType: "JSON",
            success: function (response) {
                console.log(response);
            }
        });
    }
}