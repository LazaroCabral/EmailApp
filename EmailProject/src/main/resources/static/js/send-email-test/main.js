import {AJAXUtils} from './AJAXUtils.js';
//import { jquery } from 'https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js';

const URLAccounts= new AJAXUtils('http://localhost:8080/rest/sendnow?');
const CHECKED='checked';
const UNCHECKED='';
var listAccountsId='#tbody-listOfAccounts';
var selectedAccounts=new Set();
var selectedAccountsNOT=true;
var selectAll=true;
var currentPage=0;

function getCurrentPage(){
	return this.currentPage;
}
console.log("works");
console.log(URLAccounts.URL);
