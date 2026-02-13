# Farmacia
AEE_Práctica UD5
EJERCICIO . Farmacia.
Se desea implementar la gestión de turnos en una farmacia para atender a los usuarios
(clientes) que quieran realizar cualquier tipo de compra. La farmacia dispone de cuatro
mostradores numerados del 1 al 4, éstos atenderán a los usuarios que, cuando lleguen,
cogerán el número del turno indicando para ello, el tipo de productos que van a comprar en
la farmacia.

G-General H-homeopatía O-otros

Los mostradores irán atendiendo a los usuarios según el turno asignado, independientemente
del tipo de productos que deseen comprar.
Implementaremos un servidor que se encargará de gestionar los números de los turnos de los
usuarios, quienes solicitarán el número por orden de llegada, indicando, como hemos dicho
antes, el tipo de productos a comprar, recibirán el número de turno correspondiente, lo
mostrarán y terminarán su función. El servidor deberá guardarlos para proporcionárselo al
mostrador que lo solicite, de forma que éste pueda simular la atención al usuario, con un
tiempo de retardo.

G-2seg. H-3seg O-1seg

Cada mostrador, cuando se quede libre, deberá enviar al servidor un aviso, recuperando con
el usuario que le toque, el tipo de producto que desea comprar para simular la compra y
mostrará un mensaje indicando el tipo de producto que se ha comprado. Este proceso lo hará
cada mostrador hasta que se cierre la farmacia, lo que ocurrirá cuando se apague el servidor.
