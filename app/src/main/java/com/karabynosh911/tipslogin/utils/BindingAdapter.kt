package com.karabynosh911.tipslogin.utils


//@BindingAdapter("mutableVisibility")
//fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
//    val parentActivity:AppCompatActivity? = view.getParentActivity()
//    if(parentActivity != null && visibility != null) {
//        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
//    }
//}
//
//@BindingAdapter("mutableClickable")
//fun setMutableClickability(view: View, clickable: MutableLiveData<Boolean>?) {
//    val parentActivity: AppCompatActivity? = view.getParentActivity()
//    if (parentActivity != null && clickable != null)
//        clickable.observe(parentActivity, Observer { value -> view.isClickable = clickable.value!! })
//}