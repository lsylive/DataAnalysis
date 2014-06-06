Array.prototype.append = function(obj, nodup) {   
    if(!(nodup && this.contains(obj))) {   
        this[this.length] = obj;   
    }   
}   
  
Array.prototype.contains = function(obj) {   
    return (this.indexOf(obj)>=0);   
}   
  
Array.prototype.indexOf = function(obj) {   
    var result = -1;   
    for(var i=0; i<this.length; i++) {   
        if(this[i]==obj) {   
            result = i;   
            break;   
        }   
    }   
    return result;   
}   
  
Array.prototype.clear = function() {   
    this.length = 0;   
}   
  
Array.prototype.insertAt = function(index, obj) {   
    this.splice(index, 0, obj);   
}   
  
Array.prototype.removeAt = function(index) {   
    this.splice(index, 1);   
}   
  
Array.prototype.remove = function(obj) {   
    var index = this.indexOf(obj);   
    if(index>=0)   
        this.removeAt(index);   
}   
Array.prototype.toString = function(obj) {   
    var len=this.length;   
    var retStr='';
    for(var i=0; i<this.length; i++) {   
        if(i!=this.length-1) {      
         	retStr+=this[i]+",";
        }else
        	retStr+=this[i];
    } 
    return retStr;
}   
