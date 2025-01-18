import { selectedAccountsManager, tableBodyIdAccounts } from "../main.js";
import { SelectAll } from "../buttons/SelectAll.js";
import WriteOnHTML from "./WriteOnHTML.js";

export class UpdateElementsWithEvents{

    static updateTable(arrayOfAccountsDTO){
        WriteOnHTML.printOnTable(arrayOfAccountsDTO,tableBodyIdAccounts);
        $(document).ready(()=>{
            $('input[name="accountCpf"]').on('click', ()=>{
                let clicked=SelectAll.clicked;
                if(clicked){
                    this.updateSelectAll('');
                    selectedAccountsManager.selectAll=false;
                    selectedAccountsManager.selectedAccountsNOT=true;
                }
                selectedAccountsManager.updateSelectedAccounts();
            });
        });
    }

    static updateSelectAll(check){
        WriteOnHTML.printOnSelectAll(check);
        $(document).ready(()=>{
            $('input[id="select-all-accounts-checkbox"]').on('click', ()=>{
                SelectAll.selectAllButton(tableBodyIdAccounts);
            });
        });
    }
}