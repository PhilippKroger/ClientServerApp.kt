package com.example.clientserverappkt

class FakeModel: Model<Any, Any> {

    private var callback: ResultCallback<Any, Any>?=null

    private var count = 0

    override fun fetch() {
        Thread.sleep(2000)
        if (++count%2==1) {
            callback?.provideSuccess("success")
        } else {
            callback?.provideError("error")
        }
    }

    override fun clear() {
        callback = null
    }

    override fun init(resultCallback: ResultCallback<Any, Any>) {
        callback = resultCallback
    }


}