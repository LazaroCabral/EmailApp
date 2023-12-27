import AccountDTO from "../Accounts/DTOs/AccountDTO.js";
import { selectedAccountsManager, tableBodyIdAccounts } from "../main.js";
import { UpdateElementsWithEvents } from "../writeOnHTML/UpdateElementsWithEvents.js";

export class SelectAll{
    static clicked=false;

    static async selectAllButton() {
        let isChecked=$('input[id="select-all-accounts-checkbox"]').get()[0].checked;
        let accountsDOM=$('tr[name="list-of-accounts"]').get();
        let accountsDTOs=AccountDTO.DOMAccountsToAccountDTOs(accountsDOM, isChecked);
        UpdateElementsWithEvents.updateTable(accountsDTOs, tableBodyIdAccounts);
        this.clicked=isChecked;
        selectedAccountsManager.selectAll=isChecked;
        selectedAccountsManager.selectedAccountsNOT=isChecked;
        if(isChecked) {
            selectedAccountsManager.selectedAccounts.clear();
        }
        console.log(selectedAccountsManager);
    }
}