/*******************************************************************************
* ADOBE SYSTEMS INCORPORATED
* Copyright 2007 Adobe Systems Incorporated
* All Rights Reserved.
*
* NOTICE:  Adobe permits you to use, modify, and distribute this file in
* accordance with the  terms of the Adobe license agreement accompanying it. If
* you have received this file from a source other than Adobe, then your use,
* modification, or distribution of it requires the prior written permission of
* Adobe.
*******************************************************************************/

#ifndef _INCLUDE_AE__STAGECRAFT_ASEXTENSIONS_H
#define _INCLUDE_AE__STAGECRAFT_ASEXTENSIONS_H

#include <ae/AETemplates.h>

namespace ae            {
namespace stagecraft    {

////////////////////////////////////////////////////////////////////////////////
// ASExtensions forward class declarations
class ASValue;      // a class that represents ActionScript values
class ASValueArray; // an array of ASValues used as function arguments
class ASObject;     // a class that represents an ActionScript object
class StageWindow;

////////////////////////////////////////////////////////////////////////////////
// class ASExtensionClass - a C++ class that represents an ActionScript class (an extension class)
/// Represents an ActionScript class - subclass these to surface new AS Classes into Flash.
///
/// Create your own %ASExtensionClass derived classes.  When registered,
/// the ActionScript class represented by your derived ASExtensionClass will
/// be surfaced into ActionScript.  Here's how it works:
class ASExtensionClass
{
public:  // ASExtensionClass public static 'override' functions.  Each subclass of ASExtension class
         // must implement the same set of functions with the same names and signatures
    static const char *         GetClassName();
    static void                 GetMethods(class MethodArray & methodArrayToFill);
    static bool                 FiresEvents();
	
	static ASExtensionClass *   ConstructInstance(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & constructorArguments);
	static void                 DestructInstance(StageWindow * pStageWindow, ASObject & asObject);

public:
    virtual void                OnUpdate(StageWindow * pStageWindow, ASObject & asObject) { }

public: // ASExtensionClass Types
	typedef void	            (ASExtensionClass::*MemberFunctionPointer)(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & arguments, ASValue & retValToSet);
	typedef void	                              (*StaticFunctionPointer)(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & arguments, ASValue & retValToSet);
    
    class Method
    {
    public:
        enum  Type              { kMemberFunction = 0, kStaticFunction };
        union Function          { MemberFunctionPointer m_pMemberFunction; StaticFunctionPointer m_pStaticFunction; };
    public:
        Method()                                                          : m_pName(NULL),  m_type(kMemberFunction) { m_function.m_pMemberFunction = NULL; }
		Method(const char * pName, MemberFunctionPointer pMemberFunction) : m_pName(pName), m_type(kMemberFunction) { m_function.m_pMemberFunction = pMemberFunction; }
		Method(const char * pName, StaticFunctionPointer pStaticFunction) : m_pName(pName), m_type(kStaticFunction) { m_function.m_pStaticFunction = pStaticFunction; }
    public:
        const char *		    m_pName;
        Type			        m_type;
        Function                m_function;
    };

	typedef	AEArray<Method>     MethodArray;

protected:  // ASExtensionClass construction
    ASExtensionClass()          { }
    virtual ~ASExtensionClass() { }
};

#ifndef DOXY_SKIP
class ASExtensionClassInfo
{
public:
    const char *	            (*GetClassName)();
    void			            (*GetMethods)(ASExtensionClass::MethodArray & methodArrayToSet);
    bool			            (*FiresEvents)();
    ASExtensionClass *          (*ConstructInstance)(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & constructorArguments);
    void                        (*DestructInstance)(StageWindow * pStageWindow, ASObject & asObject);
};

template <class ASEXTENSIONCLASS_T>
class ASExtension : public ASExtensionClassInfo
{
public:
	ASExtension()
	{
        GetClassName		    = ASEXTENSIONCLASS_T::GetClassName;
        GetMethods  		    = ASEXTENSIONCLASS_T::GetMethods;
        FiresEvents 		    = ASEXTENSIONCLASS_T::FiresEvents;
        ConstructInstance       = ASEXTENSIONCLASS_T::ConstructInstance;
        DestructInstance		= ASEXTENSIONCLASS_T::DestructInstance;
     }
};

////////////////////////////////////////////////////////////////////////////////
// class ASExtensionClassInfoArray - an array of ASExtensionClassInfos
typedef AEArray<ASExtensionClassInfo> ASExtensionClassInfoArray;
class ASObjectImpl;
class ASValueImpl;
#endif // #ifndef DOXY_SKIP

////////////////////////////////////////////////////////////////////////////////
// class ASValue - represents an ActionScript value
//
/// Represents an ActionScript value of type String, Number, Object, Array, or Boolean.
///
/// %class %ASValue represents an ActionScript value of type String, Number,
/// Object, Array, or Boolean.  Instances of this class are passed in arrays to
/// your extension methods.  These arrays of %ASValues represent the arguments
/// to the ActionScript function call being implemented by your C++ extension
/// method function.  Each individual %ASValue in an %ASValueArray represents a
/// typed Action Script value.  class %ASValue provides methods and operators to
/// determine the typeof, set, and get the ActionScript values as C++ values.
/// @see ASValueArray
// 
class ASValue
{
public:
    enum Type           /// Represents the type of the AS Value
    {
		kTypeVoid = 0,  ///< An uninitialized (invalid) AS value
        kTypeNull,      ///< An AS value of null
        kTypeUndefined, ///< An AS value of undefined
        kTypeInteger,   ///< An AS Number value that is an integer
        kTypeNumber,    ///< An AS Number value that is floating point
        kTypeString,    ///< An AS String value
        kTypeBoolean,   ///< An AS Value that is a Boolean
        kTypeObject     ///< An AS value that is an AS Object
    };
    
public:
    virtual Type		GetType() = 0;
        ///< Gets the ActionScript type of the value
        ///
        /// Call %GetType() to determine the Action Script type of the %ASValue.
        /// @return the %Type of the %ASValue
        
	virtual void		ReadString(AEString & stringToSet) = 0;
	    ///< Reads the ActionScript value as a string
	    ///
	    /// Call %ReadString() to fill the supplied %AEString with the value of
	    /// the %ASValue as an ActionScript String.
	    /// If the type of the %ASValue is not a String (kTypeString), then the
	    /// supplied string will be emptied.
	    /// @param stringToSet the string to set if the type of this %ASValue is a string
	    /// or to empty if the type of this %ASValue is not a string.
	    
    virtual int			ReadInt() = 0;
	    ///< Reads the ActionScript value as an integer
	    ///
	    /// Call %ReadInt() to read the %ASValue as an integer.
	    /// If the type of the %ASValue is not an integer (kTypeInteger), then %ReadInt()
	    /// will return 0.
	    /// @return the %ASValue as an integer or 0 if the %ASValue is not an integer
	    
    virtual double		ReadDouble() = 0;
	    ///< Reads the ActionScript value as a double
	    ///
	    /// Call %ReadDouble() to read the %ASValue as a double precision floating point value.
	    /// If the type of the %ASValue is not a double (kTypeNumber), then %ReadDouble()
	    /// will return 0.0.
	    /// @return the %ASValue as a double or 0.0 if the %ASValue is not an double
	    
    virtual bool		ReadBool() = 0;
	    ///< Reads the ActionScript value as a bool
	    ///
	    /// Call %ReadBool() to read the %ASValue as a boolean value.
	    /// If the type of the %ASValue is not a boolean (kTypeBoolean), then %ReadBool()
	    /// will return false.
	    /// @return the %ASValue as a boolean or false if the %ASValue is not a boolean
	    
    virtual ASObject &	ReadObject() = 0;
	    ///< Reads the ActionScript value as an %ASObject
	    ///
	    /// Call %ReadObject() to read the %ASValue as an %ASObject value.
	    /// If the type of the %ASValue is not an object (kTypeObject, kTypeMovieClip, or kTypeTextField),
	    /// then %ReadObject() will return an object whose %IsValid() function returns false.
	    /// @return the %ASValue as a boolean or false if the %ASValue is not a boolean

public:
	virtual void SetString(const char * pString) = 0;
	    ///< Sets the ActionScript value with a string value
	    ///
	    /// Call %SetString() to set the %ASValue to a string.
	    /// The type of the %ASValue will be changed to kTypeString.
	    /// Call %SetString(NULL) or %SetString("") to set the value to an empty string.
	    /// To set the %ASValue to undefined instead, call %SetUndefined() instead of %SetString().
	    /// @param pString the string to set the %ASValue with
	    /// @see SetUndefined()
	    
	virtual void SetInt(int nInt) = 0;
	    ///< Sets the ActionScript value to an integer value
	    ///
	    /// Call %SetInt() to set the %ASValue to an integer.
	    /// The type of the %ASValue will be changed to kTypeInteger.
	    /// @param nInt the integer to set the %ASValue with

	virtual void SetDouble(double val) = 0;
	    ///< Sets the ActionScript value to a double value
	    ///
	    /// Call %SetDouble() to set the %ASValue to a double.
	    /// The type of the %ASValue will be changed to kTypeNumber.
	    /// @param val the double to set the %ASValue with

	virtual void SetBool(bool bVal) = 0;
	    ///< Sets the ActionScript value to a boolean value
	    ///
	    /// Call %SetBool() to set the %ASValue to a boolean.
	    /// The type of the %ASValue will be changed to kTypeBoolean.
	    /// @param bValthe boolean to set the %ASValue with

	virtual void SetObject(ASObject & object) = 0;
	    ///< Sets the ActionScript value to an %ASObject value
	    ///
	    /// Call %SetObject() to set the %ASValue to an %ASObject.
	    /// The type of the %ASValue will be changed to kTypeObject.
	    /// @param object the object to set the %ASValue with

	virtual void SetNull() = 0;
	    ///< Sets the ActionScript value to null
	    ///
	    /// Call %SetNull() to set the %ASValue to the ActionScript value of null.
	    /// The type of the %ASValue will be changed to kTypeNull.

	virtual void SetUndefined() = 0;
	    ///< Sets the ActionScript value to undefined
	    ///
	    /// Call %SetUndefined() to set the %ASValue to the ActionScript value of undefined.
	    /// The type of the %ASValue will be changed to kTypeUndefined.


	inline operator	int()           { return ReadInt();    }
	    ///< <tt>(int)asValue</tt> casting operator to read the %ASValue as an integer.
	    ///
	    /// This casting operator allows the %ASValue to be read as an integer as follows:
	    /// <pre>int nValue = (int)asValue;</pre>
	    /// @return the %ASValue as an integer or 0 if the %ASValue is not of type kTypeInteger
	    /// @see ReadInt()
	    
	inline operator	double()        { return ReadDouble(); }
	    ///< <tt>(double)asValue</tt> casting operator to read the %ASValue as a double.
	    ///
	    /// This casting operator allows the %ASValue to be read as a double as follows:
	    /// <pre>double val = (double)asValue;</pre>
	    /// @return the %ASValue as a double or 0.0 if the %ASValue is not of type kTypeNumber
	    /// @see ReadDouble()
	    
	inline operator	bool()          { return ReadBool();   }
	    ///< <tt>(bool)asValue</tt> casting operator to read the %ASValue as a boolean.
	    ///
	    /// This casting operator allows the %ASValue to be read as a boolean as follows:
	    /// <pre>bool bVal = (bool)asValue;</pre>
	    /// @return the %ASValue as a boolean or false if the %ASValue is not of type kTypeBoolean
	    /// @see ReadBool()
	    
	inline operator	ASObject &()    { return ReadObject(); }
	    ///< <tt>(ASObject &)asValue</tt> casting operator to read the %ASValue as an object.
	    ///
	    /// This casting operator allows the %ASValue to be read as an %ASObject as follows:
	    /// <pre>ASObject & object = (ASObject &)asValue;</pre>
	    /// @return the %ASValue as an object returning an invalid object if the %ASValue is not of type kTypeObject
	    /// @see ReadObject()

	inline bool IsBool()	        { return GetType() == kTypeBoolean; }
	    ///< Determines whether or not the %ASValue is a boolean.
	    ///
	    /// Call %IsBool() as an easy way to determine whether or not the %ASValue is of type kTypeBoolean.
	    /// @return true if the %ASValue is of type kTypeBoolean, false otherwise
	    
	inline bool IsInt()	            { return GetType() == kTypeInteger; }
	    ///< Determines whether or not the %ASValue is an integer.
	    ///
	    /// Call %IsInt() as an easy way to determine whether or not the %ASValue is of type kTypeInteger.
	    /// @return true if the %ASValue is of type kTypeInteger, false otherwise
	    
	inline bool IsDouble()          { return GetType() == kTypeNumber; }
	    ///< Determines whether or not the %ASValue is a double.
	    ///
	    /// Call %IsDouble() as an easy way to determine whether or not the %ASValue is of type kTypeNumber.
	    /// @return true if the %ASValue is of type kTypeNumber, false otherwise
	    
	inline bool IsString()	        { return GetType() == kTypeString; }
	    ///< Determines whether or not the %ASValue is a string.
	    ///
	    /// Call %IsString() as an easy way to determine whether or not the %ASValue is of type kTypeString.
	    /// @return true if the %ASValue is of type kTypeString, false otherwise
	    
	inline bool IsObject()	        { return GetType() == kTypeObject; }
	    ///< Determines whether or not the %ASValue is an object.
	    ///
	    /// Call %IsObject() as an easy way to determine whether or not the %ASValue is of type kTypeObject.
	    /// @return true if the %ASValue is of type kTypeObject, false otherwise

	inline bool IsValid()	        { return GetType() != kTypeVoid; }
	    ///< Determines whether or not the %ASValue is valid.
	    ///
	    /// Call %IsValid() as an easy way to determine whether or not the %ASValue is valid
	    /// (is not of type kTypeVoid).
	    /// @return true if the %ASValue is valid, false otherwise
	    /// @see ASObject::GetProperty()

public:	    
	virtual ASValue * operator & () = 0;

#ifndef DOXY_SKIP
private:
    friend class ASValueArray;
	friend class ASObjectImpl;
    virtual void Destroy() = 0;
protected:
    ASValue()            { }
    virtual ~ASValue()   { }
#endif
};

////////////////////////////////////////////////////////////////////////////////
// class ASValueArray - an array of ASValues used as function arguments
class ASValueArray : public AEArray<ASValue *>
{
public:
	void	Append(ASValue & valueToAppend) { AEArray<ASValue *>::Append(&valueToAppend); }
};

////////////////////////////////////////////////////////////////////////////////
// class ASObject - a class representing an ActionScript object
//
/// Represents an ActionScript Object.
///
/// %class %ASObject represents an ActionScript Object.  %ASObjects passed
/// in to your C++ extension method functions represent the ActionScript
/// objects of the extension class instances whose methods are being invoked.
/// %ASObjects can also be passed and returned as method parameters, or retrived
/// as properties of other %ASObjects.  The class %ASObject provides methods
/// that allow the client, from C++, to:
/// <ul><li>create new %ASValues and %ASObjects</li>
///     <li>call ActionScript global methods</li>
///     <li>call methods of the underlying ActionScript object</li>
///     <li>get and set properties of the underlying ActionScript object</li>
///     <li>determine if the underlying AS Object is an Array, and if so, get elements of the array by index</li>
///     <li>fire events on the AS Object</li>
///     <li>enable and disable a polling update mechanism that allows for the dispatch of asynchronous notifications to ActionScript</li>
///     <li>compare ActionScript Objects for equality</li>
///     <li>influence the lifecycle of AS Objects by increasing and decreasing underlying Action Script object reference counts</li></ul> 
// 
class ASObject
{
public: // These functions pertain to the object instance
	
	virtual ASValue & CallMethod(const char * pMethodName) = 0;
        ///< Calls an ActionScript method on the underlying Object with zero arguments
        ///
        /// Call this version of %CallMethod() to call an ActionScript method on the underlying
        /// object, where the ActionScript method takes zero function parameters.<br><br>
        /// <i>Example:</i>
        /// <pre>
        /// void MyASClass::StopMovieClip(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & arguments, ASValue & retValToSet)
        /// {
        ///   %ASObject & movieClip = arguments[0]->ReadObject(); // the first argument to StopMovieClip is the movie clip to operate on
        ///   movieClip.CallMethod("stop");
        /// }</pre>
        /// @param pMethodName the name of the ActionScript method to call
        /// @return the %ASValue return value from the ActionScript method

	virtual ASValue & CallMethod(const char * pMethodName, ASValue & arg1) = 0;
        ///< Calls an ActionScript method on the underlying Object with one argument
        ///
        /// Call this version of %CallMethod() to call an ActionScript method on the underlying
        /// object, where the ActionScript method takes one function parameter.<br><br>
        /// <i>Example:</i>
        /// <pre>
        /// void MyASClass::GotoFrameOne(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & arguments, ASValue & retValToSet)
        /// {
        ///   %ASObject & movieClip = arguments[0]->ReadObject(); // the first argument to GotoOnClip is the movie clip to operate on
        ///   %ASValue & asValue = asObject.CreateASValue();
        ///   asValue.SetInt(1);  movieClip.CallMethod("gotoAndPlay", asValue);
        /// }</pre>
        /// @param pMethodName the name of the ActionScript method to call
        /// @param arg1 the first argument to the AS method
        /// @return the %ASValue return value from the ActionScript method

	virtual ASValue & CallMethod(const char * pMethodName, ASValue & arg1, ASValue & arg2) = 0;
        ///< Calls an ActionScript method on the underlying Object with two arguments
        ///
        /// Call this version of %CallMethod() to call an ActionScript method on the underlying
        /// object, where the ActionScript method takes two function parameters.
        /// @param pMethodName the name of the ActionScript method to call
        /// @param arg1 the first argument to the AS method
        /// @param arg2 the second argument to the AS method
        /// @return the %ASValue return value from the ActionScript method

	virtual ASValue & CallMethod(const char * pMethodName, ASValue & arg1, ASValue & arg2, ASValue & arg3) = 0;
        ///< Calls an ActionScript method on the underlying Object with three arguments
        ///
        /// Call this version of %CallMethod() to call an ActionScript method on the underlying
        /// object, where the ActionScript method takes three function parameters.
        /// @param pMethodName the name of the ActionScript method to call
        /// @param arg1 the first argument to the AS method
        /// @param arg2 the second argument to the AS method
        /// @param arg3 the third argument to the AS method
        /// @return the %ASValue return value from the ActionScript method

	virtual ASValue & CallMethod(const char * pMethodName, ASValueArray & methodArguments) = 0;
        ///< Calls an ActionScript method on the underlying Object with an arbitrary number of arguments
        ///
        /// Call this version of %CallMethod() to call an ActionScript method on the underlying
        /// object, where the ActionScript method takes an arbitrary number of function parameters.<br><br>
        /// <i>Example:</i>
        /// <pre>
        /// void MyASClass::SomeFunction(StageWindow * pStageWindow, ASObject & asObject, ASValueArray & arguments, ASValue & retValToSet)
        /// {
        ///   %ASObject & otherObject = arguments[0]->ReadObject(); // the first argument to this SomeFunction is an object
        ///   %ASValueArray arguments;
        ///   %ASValue & asValue1 = asObject.CreateASValue();  asValue1.SetBool(false);
        ///   %ASValue & asValue2 = asObject.CreateASValue();  asValue2.SetInt(2);
        ///   %ASValue & asValue3 = asObject.CreateASValue();  asValue3.SetString("three");
        ///   %ASValue & asValue4 = asObject.CreateASValue();  asValue4.SetObject(asObject);
        ///   arguments.Append(asValue1);
        ///   arguments.Append(asValue2);
        ///   arguments.Append(asValue3);
        ///   arguments.Append(asValue4);
        ///   object.CallMethod("methodName", asValue);  // equivalent to AS method call: otherObject.methodName(false, 2, "three", asObject);
        /// }</pre>
        /// @param pMethodName the name of the ActionScript method to call
        /// @param methodArguments the array of method arguments to call the AS method with
        /// @return the %ASValue return value from the ActionScript method
    
	virtual u32       GetNumProperties() = 0;
        ///< Gets the number of properties on an %ASObject
        ///
        /// Call %GetNumProperties() to get the number of properties that an %ASObject has.
        /// @return the number of properties that the %ASObject has.
        /// @see GetProperty()
        
	virtual ASValue & GetProperty(u32 nIndex, AEString & propertyNameToSet) = 0;
        ///< Retrieves the %ASValue property by index
        ///
        /// Call this version of %GetProperty() to retrieve a property by index.<br><br>
        /// <i>Example:</i>
        /// <pre>
        /// u32 nNumProperties = asObject.GetNumProperties();
        /// AEString propertyName;
        /// for (u32 i = 0; i < nNumProperties; i++)
        /// {
        ///   %ASValue & value = asObject.GetProperty(i, propertyName);
        ///   AETRACE("The property at index %lu has name %s\n", i, (const char *)propertyName);
        /// }
        /// </pre>
        /// @param nIndex the index from 0 to %GetNumProperties()-1
        /// @param propertyNameToSet an AEString that will be set with the name of the property at the index
        /// @return the %ASValue of the property at the index
        /// @see GetNumProperties()
	
	virtual ASValue & GetProperty(const char * pPropertyName) = 0;
        ///< Retrieves the %ASValue property by name
        ///
        /// Call this version of %GetProperty() to retrieve the %ASValue of the property with the specified name.
        /// If there is no property with the specified name, an invalid %ASValue is returned.  Use the function
        /// %ASValue::%IsValid() to determine if a valid %ASValue was returned.
        /// @param pPropertyName the name of the property to get
        /// @return the %ASValue of the property with the specified name
        /// @see ASValue::IsValid()
	
	virtual bool      SetProperty(const char * pPropertyName, const ASValue & propertyValue) = 0;
        ///< Sets a property name/value pair on the object
        ///
        /// Call %SetProperty() to set the %ASValue of the property with the specified name.
        /// @param pPropertyName the name of the property to set
        /// @param propertyValue the %ASValue the property should be set to
        /// @return true if successful, false otherwise
	
	inline bool        IsArray() { return IsInstanceOf("Array"); }
        ///< Determines whether an object is an Array
        ///
        /// Call %IsArray() to determine whether or not an object is an Array.
        /// @return true if the object is an array, false otherwise
        
    virtual u32         GetArrayLength() = 0;
        ///< Determines the length of an Array object
        ///
        /// Call %GetArrayLength() to determine the length of (number of elements in) an Array.
        /// @return the length of the array or zero if the object is not an array
        
    virtual ASValue &   GetArrayElement(u32 nIndex) = 0;
        ///< Retrieves an Array Element by index
        ///
        /// Call %GetArrayElement() to retrieve the %ASValue of an array element by index.
        /// Valid values for nIndex are 0 to %GetArrayLength()-1.  If an invalid index is specified,
        /// an invalid %ASValue is returned.<br><br>
        /// <b>Note:</b><br>
        /// To manipulate elements of the array, ActionScript Array methods should be called directly.<br>
        /// <i>Example - appending a value to the Array:</i>
        /// <pre>
        /// asObject.callMethod(                    // asObject is my Array
        ///     "push",                             // call its ActionScript method named push
        ///      asObject.CreateASValue("purple")); // with the ActionScript string value "purple"
        /// </pre>
        /// @param nIndex the index of the element to retrieve
        /// @return the %ASValue of the element at the index
        ///
        /// @see ASValue::IsValid()
        
    virtual bool		FireEvent(const char * pEventName, const ASValue & eventArg) = 0;
        ///< Fires an ActionScript event
        ///
        /// Call %FireEvent() to fire a named event.  This will result in the method with that name
        /// called on all registered AS event listeners.  This is functionally equivalent to calling
        /// asObject.CallMethod(), except that methods called with %CallMethod() may return %ASValues
        /// whereas event listener methods registered with %FireEvent may not.  Additionaly %FireEvent()
        /// may result in multiple AS event handler methods - as many as are registered for the event - whereas
        /// %CallMethod() calls a single method.
        /// @param pEventName the name of the event to fire
        /// @param eventArg the %ASValue to pass in as the argument to event handler methods
        /// @return true if event firing successful, false otherwise
        
	virtual void		StartUpdates() = 0;
	    ///< Starts per-frame extension update polling to enable asynchronous notification and/or periodic processing
	    ///
	    /// Call %StartUpdates() to start perodic calls to the %OnUpdate() virtual member function
	    /// of your %ASExtensionClass derived class.  These periodic function calls will occur in
	    /// the context of the Flash Instance thread (the same context as all %ASExtension method calls).
	    /// These periodic updates will occur once per Flash frame of animation, so the periodic rate
	    /// will be dependent on swf authoring time frame rate.
	    /// The ASExtensionClass::OnUpdate() function may be used to perform periodic step processing,
	    /// but such processing should be kept to a bare minimum as a rule, as it has the potential to affect
	    /// the performance of the Flash engine.  For serious processing tasks, your AS Extension should 
	    /// rely on a separate thread, and then check thread status (or a thread message queue) for algorithmic results.
	    /// The action of the checking the separate thread's status should be made thread safe, for example,
	    /// with an %IAEKernel::%Mutex.  In summary, %OnUpdate()  should check the status and report results back to
	    /// Action Script by calling methods on the asObject with those results using %FireEvent or %CallMethod.
	    /// In a considered system design, the action of all possible simultaneous %OnUpdate() invocations should
	    /// be considered.
	    /// @see StopUpdates()
	    /// @see FireEvent()
	    /// @see CallMethod()
	    /// @see ASExtensionClass::OnUpdate()
	    /// @see IAEKernel::Mutex
	    
	virtual void		StopUpdates() = 0;
	    ///< Stops per-frame extension update polling
	    ///
	    /// Call %StopUpdates() to stop periodic %OnUpdate() calls previously started with %StartUpdates().
	    /// @see StartUpdates()

	virtual ASObject *  AddRef() = 0;
	    ///< Adds a reference to an ASObject, returning a copy that can be cached
	    ///
	    /// Call %AddRef() to return a new %ASObject pointer that refers to the same ActionScript object.
	    /// This newly returned %ASObject pointer may be cached for future use, but only within the Flash Instance
	    /// thread as called by any of your extension's member functions, including its %OnUpdate() function.
	    /// As soon as you no longer need the ASObject pointer returned by this function, you should call
	    /// %Release() on that pointer (e.g. <code>pASObject->Release()</code>.  This will decrement the Action Script
	    /// reference count and make the %ASObject pointer invalid.  Calls to %AddRef() do not nest, as each call
	    /// to %AddRef will return a new %ASObject pointer.
	    /// @return a new %ASObject pointer representing the ActionScript object that must later be %Release()ed.
	    /// @note ActionScript objects that are MovieClip objects can not have additional %ASObject references made
	    /// to them.  When %AddRef() is called on such an object, it will return NULL.
	    /// @see IsMovieClip()
	    /// @see Release()
	    
	virtual void		Release() = 0;
	    ///< Releases ASObjects previously returned from %AddRef()
	    ///
	    /// Call %Release() on %ASObject pointers previously returned from %AddRef().
	    /// Each %ASObject pointer that is manufactured with %AddRef() must at some point be
	    /// %Release()ed or ActionScript objects will be leaked.  Cached %ASObject pointers acquired
	    /// from %AddRef() should be %Release()ed as soon as practical, no later than ASExtension
	    /// instance destruction.  As soon as %Release() is called on an %ASObject pointer,
	    /// that %ASObject pointer is made invalid and must no longer be used.
	
	virtual bool		operator == (ASObject & objectToCompareWith) = 0;
	    ///< Compares two %ASObjects for equality
	    ///
	    /// This == operator compares two %ASObjects for equality.
	    /// @returns true if the %ASObjects represent the same ActionScript object, false if they do not

	virtual	bool		IsValid() = 0;
	    ///< Determines if this %ASObject represents a valid ActionScript object
	    ///
	    /// Use %IsValid() to determine if an %ASObject returned from %ASValue::%ReadObject() represents
	    /// a valid %ASObject.
	    /// @return true if this %ASObject represents a valid ActionScript object, false otherwise
	    /// @see ASValue::ReadObject()
	    
	virtual	bool		IsInstanceOf(const char * pClassName) = 0;
	    ///< Determines if this %ASObject is an instance of an ActionScript class
	    ///
	    /// Use %IsInstanceOf() to determine whether or not an %ASObject is an instance of a specific
	    /// ActionScript class.  This function determines whether or not the %ASObject is an instance
	    /// of any class in the ActionScript class hierarchy.  So, for example, an %ASObject that
	    /// refers to a String class in ActionScript, then %IsInstanceOf("String") and %IsInstanceOf("Object")
	    /// will both return true.
	    /// @param pClassName the name of the class to determine whether this %ASObject is an instance of
	    /// @returns true of the %ASObject is an instance of the named class

	inline bool        IsMovieClip() { return IsInstanceOf("MovieClip"); }
        ///< Determines whether an object is a MovieClip
        ///
        /// Call %IsMovieClip() to determine whether or not an object is a MovieClip.
        /// MovieClip objects can not be reference counted as other objects are.
        /// @return true if the object is a MovieClip, false otherwise

	virtual ASExtensionClass * GetClassInstance() = 0;
	    ///< Returns the %ASExtensionClass C++ instance for the %ASObject
	    ///
	    /// Call %GetClassInstance() to get the %ASExtensionClass derived C++ instance that represents
	    /// this %ASObject.  Typically there is no need to call %GetClassInstance() since the %ASObject passed
	    /// into extension member functions will already be the C++ this pointer.  In other words,
	    /// <code>this == asObject.GetClassInstance()</code> for the <code>%ASObject & asObject</code> reference
	    /// passed into the extension method functions.  %GetClassInstance() is useful when passing
	    /// extension class objects as arguments to extension functions, or when an %ASObject property
	    /// on an object is another object that is an extension class.  If the ActionScript type is known,
	    /// then the return value of %ASExtensionClass can be casted to a concrete %ASExtensionClass
	    /// subclass instance.
	    /// @return the %ASExtensionClass instance of the %ASObject or NULL if the %ASObject is not an extension class

	virtual ASValue &	CreateASValue() = 0;
	    ///< Creates an %ASValue that must then be set with a C++ value
	    ///
	    /// Call this version of %CreateASValue() to create an uninitialized %ASValue that then
	    /// must be set with one of the ASValue::Set...() functions.  Until the %ASValue is set
	    /// with a valid value, %GetType() on the returned %ASValue() will return %ASValue::%kTypeVoid.
	    /// @return an unset %ASValue
	     
    virtual ASValue &   CreateASValue(int nInt) = 0;
        ///< Creates an %ASValue as an integer value
        ///
        /// This version of %CreateASValue() creates an %ASValue as an integer (type ASValue::kTypeInteger)
        /// and sets its value with the supplied integer value.
        /// @param nInt the integer to create the %ASValue with
        /// @return the created %ASValue of type kTypeInteger
        
    virtual ASValue &   CreateASValue(const char * pString) = 0;
        ///< Creates an %ASValue as a String value
        ///
        /// This version of %CreateASValue() creates an %ASValue as an string (type ASValue::kTypeString)
        /// and sets its value with the supplied string.
        /// @param pString the string to create the %ASValue with
        /// @return the created %ASValue of type kTypeString
    
    virtual ASValue &   CreateASValue(double val) = 0;
        ///< Creates an %ASValue as a Number (floating point) value
        ///
        /// This version of %CreateASValue() creates an %ASValue as Number (type ASValue::kTypeNumber)
        /// and sets its value with the supplied double value.
        /// @param val the double precision floating point number to create the %ASValue with
        /// @return the created %ASValue of type kTypeNumber

    virtual ASValue &   CreateASValue(bool bVal) = 0;
        ///< Creates an %ASValue as a Boolean value
        ///
        /// This version of %CreateASValue() creates an %ASValue as a Boolean (type ASValue::kTypeBoolean)
        /// and sets its value with the supplied boolean value.
        /// @param bVal the boolean value to create the %ASValue with
        /// @return the created %ASValue of type kTypeBoolean

    virtual ASObject &	CreateASObject(const char * pClassName) = 0;
        ///< Creates a new %ASObject of the specified class without constructor arguments
        ///
        /// Call this version of %CreateASObject() to create a new ActionScript object of the
        /// specified class, without constructor arguments.<br>
        /// <i>Example:</i><br>
        /// <code>ASObject & myString = asObject.CreateASObject("String");</code><br>
        /// is equivalent to the ActionScript:<br>
        /// <code>var myString:String = new String();<br></code>
        /// @param pClassName the class name of the object to create
        /// @return the %ASObject representing the new ActionScript object
        /// @note The returned %ASObject & has an internal reference count of 1 and will
        /// be garbage collected unless the ASObject is passed as an argument to an ActionScript method
        /// that creates another reference to it, unless the returned %ASObject is set as a property
        /// of another %ASObject, or unless %ASObject::%AddRef() is called on the returned %ASObject.
        /// @see AddRef()
       
    virtual ASObject &	CreateASObject(const char * pClassName, ASValueArray & constructorArguments) = 0;
        ///< Creates a new %ASObject of the specified class with constructor arguments
        ///
        /// Call this version of %CreateASObject() to create a new ActionScript object of the
        /// specified class, with constructor arguments.<br>
        /// <i>Example:</i><br>
        /// <code> ASValueArray arguments;<br>
        /// arguments.Append(asObject.CreateASValue("The quick brown fox"));<br>
        /// ASObject & myString = asObject.CreateASObject("String", arguments);<br>
        /// </code>
        /// is equivalent to the ActionScript:<br>
        /// <code>var myString:String = new String("The quick brown fox");<br></code>
        /// @param pClassName the class name of the object to create
        /// @param constructorArguments the %ASValueArray of %ASValues to be passed as arguments to the object constructor
        /// @return the %ASObject representing the new ActionScript object
        /// @note The returned %ASObject & has an internal reference count of 1 and will
        /// be garbage collected unless the ASObject is passed as an argument to an ActionScript method
        /// that creates another reference to it, unless the returned %ASObject is set as a property
        /// of another %ASObject, or unless %ASObject::%AddRef() is called on the returned %ASObject.
        /// @see AddRef()
    
	virtual ASValue &   CallGlobalMethod(const char * pMethodName, ASValueArray & methodArguments) = 0;
	    ///< Calls a global ActionScript method
	    ///
	    /// Use %CallGlobalMethod() to call an %ActionScript global method.
	    /// @param pMethodName the name of the global method to call
	    /// @param methodArgumetns the %ASValueArray of %ASValues to be passed as arguments to the global method.

public:
	virtual ASObject * operator & () = 0;

#ifndef DOXY_SKIP
protected:
    ASObject()          { }
    virtual ~ASObject() { }
	friend class ASValueImpl;
	friend class ASObjectImpl;
#endif
};

}} // end namespace ae::stagecraft

#endif // #ifndef _INCLUDE_AE__STAGECRAFT_ASEXTENSIONS_H

////////////////////////////////////////////////////////////////////////////////
//  LOG
////////////////////////////////////////////////////////////////////////////////
//  11-Aug-08   dwoodward   created
//  19-Sep-08   dwoodward   added StageWindow * parameter to method calls
//                          removed redundant pClientInstance parameter from method calls 
//  03-Mar-08   dwoodward   no trailing commas on the last item in an enum
//	09-Mar-09	abhardwa	EDK Enhancements
//  16-Mar-09   dwoodward   ASExtensionClass instances now directly represent actual AS class instances
//                          instead of using the untyped void * ASExtensionClass::ClassInstance
//	19-Mar-09	abhardwa	EDK Enhancements part II. Making ASValue and ASObject easier to use.
//  20-Mar-09   dwoodward   added array functions
//  30-Mar-09   dwoodward   doxygenated, phew!  Streamlined ASValue types.
//  15-Aug-09   dwoodward   made address-of operators on ASObject and ASValue public
