package com.ps.fragstacker

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes

/**
 * Created by Prokash Sarkar on 8/4/2019.
 * https://github.com/prokash-sarkar
 */

class FragmentTransactionOptions private constructor(builder: Builder) {

    @AnimatorRes
    @AnimRes
    var enter = 0

    @AnimatorRes
    @AnimRes
    var exit = 0

    @AnimatorRes
    @AnimRes
    var popEnter = 0

    @AnimatorRes
    @AnimRes
    var popExit = 0

    init {
        this.enter = builder.enter
        this.exit = builder.exit
        this.popEnter = builder.popEnter
        this.popExit = builder.popExit
    }

    class Builder {

        @AnimatorRes
        @AnimRes
        internal var enter: Int = 0

        @AnimatorRes
        @AnimRes
        internal var exit: Int = 0

        @AnimatorRes
        @AnimRes
        internal var popEnter: Int = 0

        @AnimatorRes
        @AnimRes
        internal var popExit: Int = 0

        fun setCustomAnimations(@AnimatorRes @AnimRes enter: Int, @AnimatorRes @AnimRes exit: Int,
                                @AnimatorRes @AnimRes popEnter: Int, @AnimatorRes @AnimRes popExit: Int): Builder {
            this.enter = enter
            this.exit = exit
            this.popEnter = popEnter
            this.popExit = popExit
            return this
        }

        fun setCustomAnimations(@AnimatorRes @AnimRes enter: Int, @AnimatorRes @AnimRes exit: Int): Builder {
            this.exit = exit
            this.enter = enter
            return this
        }

        fun build(): FragmentTransactionOptions {
            return FragmentTransactionOptions(this)
        }
    }

}
