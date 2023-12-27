import AccountDTO from "./DTOs/AccountDTO.js";
import { UpdateElementsWithEvents } from "../writeOnHTML/UpdateElementsWithEvents.js";
export default class SelectedAccountsManager{

    constructor(tableBodyId){
        this.tableBodyId='#tbody-listOfAccounts';
        this.CHECKED=true;
        this.UNCHECKED=false;
        
        this.selectedAccounts=new Set();
        this.selectedAccountsNOT=false;
        this.selectAll=false;
    }

    writeAccounts(accounts) {
        $(this.tableBodyId).empty();
        let printAccounts=[];
        let check= this.selectedAccountsNOT ? this.UNCHECKED:this.CHECKED;
        let checkNOT= this.selectedAccountsNOT ? this.CHECKED:this.UNCHECKED;
        
        $.each(accounts, (indexInArray, valueOfElement)=>{
            
            if(this.selectedAccounts.has(valueOfElement.cpf)) {
                let write=new AccountDTO(valueOfElement.cpf, 
                    valueOfElement.name, valueOfElement.email, check);
                printAccounts.push(write);
            }else {
                let write=new AccountDTO(valueOfElement.cpf, 
                    valueOfElement.name, valueOfElement.email, checkNOT);
                printAccounts.push(write);
            }

        });
        UpdateElementsWithEvents.updateTable(printAccounts);
        
    }

    updateSelectedAccounts() {

        let checkedAtributes=$('input[name="accountCpf"]:checked').get();
        let uncheckedAtributes=$('input:checkbox[name="accountCpf"]:not(:checked)').get();
    
        let add;
        let remove;

        if(this.selectedAccountsNOT){
            remove=checkedAtributes;
            add=uncheckedAtributes;
        }else{
            add=checkedAtributes;
            remove=uncheckedAtributes;
        }
        $.each(add, (indexInArray, valueOfElement)=>{
            this.selectedAccounts.add(valueOfElement.value);
        })
    
        $.each(remove, (indexInArray, valueOfElement)=>{
            this.selectedAccounts.delete(valueOfElement.value);
        });
    
    }
}