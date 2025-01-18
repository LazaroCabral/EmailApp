import AJAXUtils from './AJAXUtils.js';
import { BackAndNext } from './buttons/BackAndNext.js';
import SelectedAccountsManager from './Accounts/SelectedAccountsManager.js';
import { SelectAll } from './buttons/SelectAll.js';
import { AccountsPOSTDTO } from './Accounts/DTOs/AccountsPOSTDTO.js';
import WriteOnHTML from './writeOnHTML/WriteOnHTML.js';
//import { jquery } from 'https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js';

var url='http://localhost:8080/rest/sendnow';
const ajaxUtils= new AJAXUtils('http://localhost:8080/rest/sendnow');
const backAndNextButtons=new BackAndNext(new AJAXUtils('http://localhost:8080/rest/sendnow'), '#tbody-listOfAccounts');
export var selectedAccountsManager=new SelectedAccountsManager();
var writeOnHTML=new WriteOnHTML();
export var tableBodyIdAccounts='#tbody-listOfAccounts';
var emailTemplatesId='#email-templates';
var subjectId="#subject-section"

var emailModel=[];
var subject="";

async function defaultListAccounts(){
    let data=await AJAXUtils.getJSON(ajaxUtils.url);
    selectedAccountsManager.writeAccounts(data.accounts);
    //console.log(data.emailTemplates);
    WriteOnHTML.printOnEmailTemplates(data.emailTemplates, emailTemplatesId);
    WriteOnHTML.printOnSubject(data.subject, subjectId);
    console.log(new AccountsPOSTDTO(data.accounts,false,false,data.emailTemplates[0],"Assunto é"))
    console.log($('section[id="subject-section"]' , $('select[id="email-templates"]:selected')));
    //$('#s').append('<button id="selected">selected</button>');
}

defaultListAccounts();

$(document).ready(()=>{
    $('#s').on('click', ()=>{
        console.log(selectedAccountsManager);
        console.log('convert accounts :')
        console.log(JSON.stringify(Array.from(selectedAccountsManager.selectedAccounts.values())));
    });
    $('input[id="select-all-accounts-checkbox"]').click(()=>{
        SelectAll.selectAllButton();
    });
    $('#next-page').click(()=>{
        backAndNextButtons.nextPage(selectedAccountsManager);
    });
    $('#back-page').click(()=>{
        backAndNextButtons.backPage(selectedAccountsManager);
    });
    $('#submit-accounts').click(()=>{



        //SubmitAccounts.accountsPOST(ajaxUtils.url, 
        let obj=new AccountsPOSTDTO(Array.from(selectedAccountsManager.selectedAccounts.values()),
            selectedAccountsManager.selectedAccountsNOT,
            selectedAccountsManager.selectAll,
            $('option[name="email-template"]:selected').get()[0].value,
            $('input[id="email-subject"]').get()[0].value);

        console.log(obj);
        console.log('string :')
        console.log(JSON.stringify(obj))
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(obj),
            contentType: "application/json",
            success: function (response) {
                console.log(response);
            }
        });
        
       // );
    });
    }
);
