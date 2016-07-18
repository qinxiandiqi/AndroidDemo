if(!window.js_bridge){
    window.js_bridge = {
        onEvent:function(a){
            var s = "{";
            for(var item in a){
                s += item;
                s += ":";
                console.log(typeof a[item]);
                switch(typeof a[item]){
                    case "string":
                        s += '"' + a[item].toString() + '"';
                        break;
                    case "function":
                        s += '"' + a[item].toString().replace(/"/g, "\\\\\\\"").replace(/\n/g, "") + '"';
                        break;
                    case "number":
                        s += '"' + new Number(a[item]).toString() + '"';
                        break;
                    case "object":
                        s += "null"
                        break;
                    default:
                        s += a[item];
                }
                s += ",";
            }
            s = s.substr(0, s.length -1);
            s += "}";
            console.log(s);
            return prompt(a.action, s);
        }
    }
}