export class AJAXUtils {

    constructor(url){
        this.URL=url;
        //"http://localhost:8080/rest/sendnow?";
    }

    URLWithQuery(page,size,name,cpf) {
        let url=URL
        let and='';

        if(page!=null) {
            url=url+and+'page='+page;
            and='&';
        }

        if(size!=null) {
            url=url+and+'size='+size;
            and='&';
        }

        if(name!=null) {
            url=url+and+'name='+name;
            and='&';
        }

        if(cpf!=null) {
            url=url+and+'cpf='+cpf;
        }

        return url;
    }
}