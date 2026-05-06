package io.github.numq.usecase.raw

sealed interface UseCase<in Input, out Output> {
    suspend operator fun invoke(input: Input): Output

    interface Action : UseCase<Unit, Unit> {
        suspend fun action()

        override suspend fun invoke(input: Unit): Unit = action()

        suspend operator fun invoke(): Unit = invoke(input = Unit)
    }

    interface Query<out Output> : UseCase<Unit, Output> {
        suspend fun query(): Output

        override suspend fun invoke(input: Unit): Output = query()

        suspend operator fun invoke(): Output = invoke(input = Unit)
    }

    interface Command<in Input> : UseCase<Input, Unit> {
        suspend fun command(input: Input)

        override suspend fun invoke(input: Input): Unit = command(input = input)
    }

    interface Exchange<in Input, out Output> : UseCase<Input, Output> {
        suspend fun exchange(input: Input): Output

        override suspend fun invoke(input: Input): Output = exchange(input = input)
    }
}