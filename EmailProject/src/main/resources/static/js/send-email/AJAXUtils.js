
export default class AJAXUtils {

    constructor(url){
        this.url=url;
        //"http://localhost:8080/rest/sendnow?";
    }

    static async getJSON(url) {
        console.log("JSON works");
        return await $.getJSON(url, data=>{
            let accounts=data.accounts;
            return data;
        });
    }
    
}