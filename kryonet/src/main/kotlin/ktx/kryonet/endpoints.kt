package ktx.kryonet

import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.EndPoint
import com.esotericsoftware.kryonet.Listener

/**
 * Listen to when the remote end of the [EndPoint] has been connected. This will be invoked before [EndPoint.onReceive]
 * and on the same thread as [EndPoint.update]. This method should not block for long periods as other network activity
 * will not be processed until it returns.
 *
 * @param body The function to invoke when the [Connection] has been connected.
 */
inline fun EndPoint.onConnect(crossinline body: (conn: Connection) -> Unit) {
  addListener(object : Listener() {
    override fun connected(conn: Connection) {
      body.invoke(conn)
    }
  })
}

/**
 * Listen to when the remote end is no longer connected. There is no guarantee as to what thread will invoke this method.
 *
 * @param body The function to invoke when the [Connection] has been connected.
 */
inline fun EndPoint.onDisconnect(crossinline body: (conn: Connection) -> Unit) {
  addListener(object : Listener() {
    override fun disconnected(conn: Connection) {
      body.invoke(conn)
    }
  })
}

/**
 * Listen to when an object of type [T] has been received from the remote end of the connection. This will be invoked
 * on the same thread as [EndPoint.update]. This method should not block for long periods as other network activity
 * will not be processed until it returns.
 *
 * @param body The function to invoke when an object of type [T] has been received from the [Connection].
 * @param T The type of the object to be received
 */
inline fun <reified T : Any> EndPoint.onReceive(crossinline body: (conn: Connection, obj: T) -> Unit) {
  addListener(object : Listener() {
    override fun received(conn: Connection, obj: Any) {
      if (obj is T)
        body.invoke(conn, obj)
    }
  })
}

/**
 * Listen to when the connection is below the idle threshold ([Connection.setIdleThreshold]).
 *
 * @param body The function to invoke when the connection is below the idle threshold.
 */
inline fun EndPoint.onIdle(crossinline body: (conn: Connection) -> Unit) {
  addListener(object : Listener() {
    override fun idle(conn: Connection) {
      body.invoke(conn)
    }
  })
}