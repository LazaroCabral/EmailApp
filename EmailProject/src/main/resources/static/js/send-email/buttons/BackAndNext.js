import AJAXUtils from "../AJAXUtils.js";
import SelectedAccountsManager from "../Accounts/SelectedAccountsManager.js";
import WriteOnHTML from "../writeOnHTML/WriteOnHTML.js";

export class BackAndNext{
    constructor(ajaxUtils){
        this.ajaxUtils=ajaxUtils;
        this.currentPage=0;
    }

    async backPage(selectedAccountsManager) {
        console.log("back page works");
        let urlWithQuery=this.ajaxUtils.urlWithQuery(this.currentPage-1,'1');
        console.log(this.currentPage);
        if(this.currentPage>0){
            let data=await AJAXUtils.getJSON(urlWithQuery);
            console.log("Cdc")
            console.log(data);
            if(data.accounts.length==0) {
                return;
            }
            this.updateAndWrite(selectedAccountsManager, data);
        }else {
            return;
        }
        //console.log(selectedAccounts);
        console.log('teste fff')
    }
    
    async nextPage(selectedAccountsManager){
        console.log("nextPage works");
        let urlWithQuery=this.ajaxUtils.urlWithQuery(this.currentPage+1, '1');
        if(this.currentPage>=0){
            console.log(this.currentPage+1);
            let data=await AJAXUtils.getJSON(urlWithQuery);
            console.log(data);
            if(data.accounts.length==0) {
                console.log('lentgthy hc');
                return;
            }
            console.log("vvv")
            this.updateAndWrite(selectedAccountsManager, data);
            console.log(selectedAccountsManager.selectedAccounts);
        }else {

            window.alert('erro!!\nrecarregue a pagina')
        }
        //console.log(selectedAccounts);
    
    }

    updateAndWrite(selectedAccountsManager, data){
        console.log("upwrite");
        selectedAccountsManager.updateSelectedAccounts();
        selectedAccountsManager.writeAccounts(data.accounts);
        this.currentPage=data.currentPage;
        console.log(selectedAccountsManager.selectedAccounts);
    }
}