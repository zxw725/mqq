export function resetForm(form, callback){
    form.resetFields();
    typeof callback == 'function'?callback() : '';
}
